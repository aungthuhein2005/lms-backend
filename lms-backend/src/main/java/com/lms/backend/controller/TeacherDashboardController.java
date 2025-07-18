package com.lms.backend.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.backend.dto.RecentExamDTO;
import com.lms.backend.dto.TeacherCourseProgressDTO;
import com.lms.backend.entity.Assignment;
import com.lms.backend.entity.ClassEntity;
import com.lms.backend.entity.Course;
import com.lms.backend.entity.Exam;
import com.lms.backend.repository.AssignmentRepository;
import com.lms.backend.repository.AssignmentSubmissionRepository;
import com.lms.backend.repository.ClassRepository;
import com.lms.backend.repository.EnrollmentRepository;
import com.lms.backend.repository.StudentRepository;
import com.lms.backend.service.CourseProgressService;
import com.lms.backend.service.EnrollmentService;
import com.lms.backend.service.ExamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/teacher-dashboard")
@RequiredArgsConstructor
public class TeacherDashboardController {
	
	@Autowired private EnrollmentService studentClassService;
	@Autowired private EnrollmentRepository enrollmentRepository;
	@Autowired private AssignmentRepository assignmentRepository;
	@Autowired private StudentRepository studentRepo;
	@Autowired private AssignmentRepository assignmentRepo;
	@Autowired private AssignmentSubmissionRepository submissionRepo;
	@Autowired private ClassRepository classRepository;
	
	@Autowired private CourseProgressService courseProgressService;
	@Autowired private ExamService examService;
	
	@GetMapping("/{teacherId}/assigned_classes_count")
	public ResponseEntity<Integer> getAssignedClassesCount(@PathVariable int teacherId) {
		System.out.println(teacherId);
	    List<ClassEntity> classes = classRepository.findByTeacherId(teacherId);
	    int count = classes.size();
	    return ResponseEntity.ok(count);
	}
	
	@GetMapping("/{teacherId}/assigned_courses_count")
	public ResponseEntity<Integer> getAssignedCourseCount(@PathVariable int teacherId) {
		List<ClassEntity> assignedClasses = classRepository.findByTeacherId(teacherId);
		List<Course> assignedCourses = assignedClasses.stream()
													.map(cls->cls.getCourse())
													.collect(Collectors.toList());
	    int count = assignedCourses.size();
	    return ResponseEntity.ok(count);
	}
	
	@GetMapping("/{teacherId}/near-deadline-assignments")
	public List<?> getNearDeadlineAssignments(@PathVariable int teacherId) {
	    LocalDate now = LocalDate.now();
	    LocalDate threshold = now.plusDays(3);
	    List<Assignment> assignements = assignmentRepo.findNearDeadlineAssignmentsByTeacherId(teacherId,now,threshold);
	    return assignements;
	}
	
	@GetMapping("/{teacherId}/course-progress")
	public List<TeacherCourseProgressDTO> courseProgressDTOs(@PathVariable int teacherId) {
	    return courseProgressService.getTeacherDashboardCourseProgress(teacherId);
	}
	
	@GetMapping("/{teacherId}/recent-exams")
	public List<RecentExamDTO> recentExams(@PathVariable int teacherId) {
	    return examService.getRecentExamByTeacherId(teacherId);
	}



}
