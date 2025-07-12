package com.lms.backend.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.backend.dto.ClassDTO;
import com.lms.backend.dto.EnrollmentRequest;
import com.lms.backend.dto.StudentDTO;
import com.lms.backend.dto.StudentRequest;
import com.lms.backend.entity.ClassEntity;
import com.lms.backend.entity.Student;
import com.lms.backend.service.StudentUpdateRequest;
import com.lms.backend.service.UserService;
import com.lms.backend.service.impl.EnrollmentServiceImpl;
import com.lms.backend.service.impl.StudentServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

	private final StudentServiceImpl studentServiceImpl;
	private final EnrollmentServiceImpl studentClassServiceImpl;
	private final UserService userService;
	
	@GetMapping
	public ResponseEntity<List<StudentDTO>> getAllStudents(){
		return ResponseEntity.ok(studentServiceImpl.getAllStudents());
	}
	
	@PostMapping
	public ResponseEntity<?> createStudent(@RequestBody StudentRequest studentRequest) {
	    if (userService.hasUser(studentRequest.getUserId())) {
	        Student student = studentServiceImpl.createStudent(studentRequest);
	        return ResponseEntity.ok(Map.of(
	        		"message", "Student added successfully",
	        		"student", student));
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                             .body(Map.of("message", "User not found"));
	    }
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable int id){
		return ResponseEntity.ok(studentServiceImpl.getStudentById(id));
	}
	
	@GetMapping("/assigned_classes/{studentId}")
	public ResponseEntity<List<ClassDTO>> getAssignedClasses(@PathVariable int studentId) {
	    List<ClassEntity> classes = studentClassServiceImpl.getAssignedClassesByStudentId(studentId);
	    List<ClassDTO> result = classes.stream()
                .map(ClassDTO::new)
                .collect(Collectors.toList());
	    return ResponseEntity.ok(result);
	}

	
	@PostMapping("/assign_to_class")
	public ResponseEntity<Void> assignStudentToClass(@RequestBody EnrollmentRequest assignRequest){
		studentClassServiceImpl.assignStudent(assignRequest);
		return ResponseEntity.ok().build();
	}
	
	@PatchMapping("/soft_delete/{id}")
	public ResponseEntity<Void> softDeleteStudent(@PathVariable int id){
		studentServiceImpl.softDeleteById(id);
	    return ResponseEntity.noContent().build(); 
	}
	
	@PatchMapping("/restore/{id}")
	public ResponseEntity<Void> restoreStudent(@PathVariable int id){
		studentServiceImpl.restoreById(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(
            @PathVariable int id,
            @RequestBody StudentUpdateRequest request
    ) {
        try {
            Student updatedStudent = studentServiceImpl.updateStudent(id, request);
            return ResponseEntity.ok().body(Map.of("message","Updgate Success","student",updatedStudent));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // Or return a custom error response
        }
    }
}
