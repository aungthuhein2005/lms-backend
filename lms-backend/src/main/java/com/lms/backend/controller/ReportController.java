package com.lms.backend.controller;

import com.lms.backend.dto.*;
import com.lms.backend.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // 1. Student Grade Report
    @GetMapping("/student-grade")
    public List<StudentGradeReportDTO> getStudentGradeReport() {
        return reportService.getStudentGradeReport();
    }

    // 2. Teacher Attendance Report
//    @GetMapping("/teacher-attendance/{teacherId}")
//    public List<TeacherAttendanceReportDTO> getTeacherAttendanceReport(@PathVariable int teacherId) {
//        return reportService.getTeacherAttendanceReport(teacherId);
//    }

    // 3. Class Performance Summary
    @GetMapping("/class-performance")
    public List<ClassSummaryDTO> getClassPerformanceSummary() {
        return reportService.getClassPerformanceSummary();
    }

    // 4. Course Enrollment Summary
    @GetMapping("/course-enrollment")
    public List<CourseEnrollmentSummaryDTO> getCourseEnrollmentSummary() {
        return reportService.getCourseEnrollmentSummary();
    }
}
