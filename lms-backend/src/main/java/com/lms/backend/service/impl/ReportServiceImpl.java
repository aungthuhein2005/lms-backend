package com.lms.backend.service.impl;

import com.lms.backend.dto.*;
import com.lms.backend.service.ReportService;
import com.lms.backend.repository.*;
import com.lms.backend.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    
    @Autowired StudentExamResultRepository examResultRepository;

    // 1. Student Grade Report
    @Override
    public List<StudentGradeReportDTO> getStudentGradeReport() {
        return ((Collection<StudentExamResult>) examResultRepository.findAll()).stream().map(result -> {
            StudentGradeReportDTO dto = new StudentGradeReportDTO();
            dto.setStudentId(result.getStudent().getId());
            dto.setStudentName(result.getStudent().getUser().getName());
            dto.setClassName(result.getExam().getClasses().getName());
            dto.setSubject(result.getExam().getClasses().getCourse().getSubject().getName()); 
            dto.setGrade(result.getGrade());
            return dto;
        }).collect(Collectors.toList());
    }


    // 2. Teacher Attendance Report
//    @Override
//    public List<TeacherAttendanceReportDTO> getTeacherAttendanceReport(int teacherId) {
//        return attendanceRepository.findByTeacherId(teacherId).stream().map(att -> {
//            TeacherAttendanceReportDTO dto = new TeacherAttendanceReportDTO();
//            dto.setDate(att.getDate());
//            dto.setStatus(att.getStatus());
//            dto.setSubject(att.getSubject().getName());
//            return dto;
//        }).collect(Collectors.toList());
//    }

    // 3. Class Performance Summary
    @Override
    public List<ClassSummaryDTO> getClassPerformanceSummary() {
        List<ClassEntity> classes = (List<ClassEntity>) classRepository.findAll();

        List<ClassSummaryDTO> summaryList = new ArrayList<>();

        for (ClassEntity classEntity : classes) {
            int classId = classEntity.getId();
            List<StudentExamResult> results = examResultRepository.findResultsByClassId(classId);

            double averageScore = results.stream()
                .mapToDouble(StudentExamResult::getScore)
                .average()
                .orElse(0.0);

            ClassSummaryDTO dto = new ClassSummaryDTO();
            dto.setClassId(classId);
            dto.setClassName(classEntity.getName()); 
            dto.setStudentCount(results.size());
            dto.setAverageScore(averageScore);

            summaryList.add(dto);
        }

        return summaryList;
    }


    // 4. Course Enrollment Summary
    @Override
    public List<CourseEnrollmentSummaryDTO> getCourseEnrollmentSummary() {
        List<Course> courses = courseRepository.findAll();
        List<CourseEnrollmentSummaryDTO> summaryList = new ArrayList<>();

        for (Course course : courses) {
            int courseId = course.getId();
            List<Enrollment> enrollments = enrollmentRepository.findByCourseId(courseId);

            CourseEnrollmentSummaryDTO dto = new CourseEnrollmentSummaryDTO();
            dto.setCourseId(courseId);
            dto.setCourseName(course.getTitle());
            dto.setStudentCount(enrollments.size());

            summaryList.add(dto);
        }

        return summaryList;
    }

}
