package com.lms.backend.controller;

import java.util.List; 

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.backend.dto.StudentDTO;
import com.lms.backend.entity.Student;
import com.lms.backend.service.impl.StudentServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

	private final StudentServiceImpl studentServiceImpl;
	
	@GetMapping
	public ResponseEntity<List<StudentDTO>> getAllStudents(){
		return ResponseEntity.ok(studentServiceImpl.getAllStudents());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable int id){
		return ResponseEntity.ok(studentServiceImpl.getStudentById(id));
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
}
