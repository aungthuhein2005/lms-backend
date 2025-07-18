package com.lms.backend.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.backend.dto.AssignmentWithStatusDTO;
import com.lms.backend.dto.ClassDTO;
import com.lms.backend.entity.Assignment;
import com.lms.backend.entity.AssignmentSubmission;
import com.lms.backend.entity.ClassEntity;
import com.lms.backend.entity.Course;
import com.lms.backend.entity.Enrollment;
import com.lms.backend.entity.Student;
import com.lms.backend.repository.AssignmentRepository;
import com.lms.backend.repository.AssignmentSubmissionRepository;
import com.lms.backend.repository.EnrollmentRepository;
import com.lms.backend.repository.StudentRepository;
import com.lms.backend.service.CourseProgressService;
import com.lms.backend.service.EnrollmentService;
import com.lms.backend.service.StudentService;
import com.lms.backend.service.impl.StudentServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/student-dashbaord")
@RequiredArgsConstructor
public class StudentDashboard {
	
	@Autowired private EnrollmentService studentClassService;
	@Autowired private EnrollmentRepository enrollmentRepository;
	@Autowired private StudentRepository studentRepo;
	@Autowired private AssignmentRepository assignmentRepo;
	@Autowired private AssignmentSubmissionRepository submissionRepo;
	
	@GetMapping("/{studentId}/assigned_classes_count")
	public ResponseEntity<Integer> getAssignedClassesCount(@PathVariable int studentId) {
	    List<ClassEntity> classes = studentClassService.getAssignedClassesByStudentId(studentId);
	    int count = classes.stream()
                .map(ClassDTO::new)
                .collect(Collectors.toList()).size();
	    return ResponseEntity.ok(count);
	}
	
	@GetMapping("/{studentId}/assigned_courses_count")
	public ResponseEntity<Integer> getAssignedCourseCount(@PathVariable int studentId) {
		List<Enrollment> enrolledClasses = enrollmentRepository.findByStudentId(studentId);
		List<Course> enrolledCourses = enrolledClasses.stream()
													.map(cls->cls.getClassEntity().getCourse())
													.collect(Collectors.toList());
	    int count = enrolledCourses.size();
	    return ResponseEntity.ok(count);
	}
	
	@GetMapping("/{studentId}/near-deadline-assignments")
	public List<?> getNearDeadlineAssignments(@PathVariable int studentId) {
	    LocalDate now = LocalDate.now();
	    LocalDate threshold = now.plusDays(3);

	    Student student = studentRepo.findById(studentId)
	        .orElseThrow(() -> new RuntimeException("Student not found"));

	    List<Integer> classIds = student.getStudentClasses().stream()
	        .map(enroll -> enroll.getClassEntity().getId())
	        .toList();

	    if (classIds.isEmpty()) {
	        return Collections.emptyList(); // No classes, so no assignments
	    }
	    
	    List<AssignmentSubmission> submissions = submissionRepo.findByStudent_id(studentId);

	    // Extract submitted assignment IDs
	    Set<Integer> submittedAssignmentIds = submissions.stream()
	        .map(sub -> sub.getAssignment().getId())
	        .collect(Collectors.toSet());

	    List<Assignment> assignments = assignmentRepo.findNearDeadlineAssignmentsByClassIds(classIds, now, threshold);
	    System.out.println(assignments.size());
	    List<AssignmentWithStatusDTO> result = assignments.stream()
		        .map(assignment -> {
		            AssignmentWithStatusDTO dto = new AssignmentWithStatusDTO();
		            dto.setId(assignment.getId());
		            dto.setTitle(assignment.getTitle());
		            dto.setDescription(assignment.getDescription());
		            dto.setDueDate(assignment.getDueDate());
		            dto.setPoint(assignment.getPoint());
		            dto.setMedia(assignment.getMedia());
		            dto.setClassname(assignment.getClassEntity().getName());
		            dto.setTeacher(assignment.getTeacher().getUser().getName());
		            dto.setStatus(submittedAssignmentIds.contains(assignment.getId()) ? "Submitted" : "Pending");
		            return dto;
		        })
		        .collect(Collectors.toList());
	    return result;
	}


}
