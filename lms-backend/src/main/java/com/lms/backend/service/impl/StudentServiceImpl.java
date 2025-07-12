package com.lms.backend.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.backend.dto.StudentDTO;
import com.lms.backend.dto.StudentRequest;
import com.lms.backend.entity.Student;
import com.lms.backend.entity.User;
import com.lms.backend.repository.StudentRepository;
import com.lms.backend.repository.UserRepository;
import com.lms.backend.service.StudentService;
import com.lms.backend.service.StudentUpdateRequest;
import com.lms.backend.utils.DtoConverter;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<StudentDTO> getAllStudents() {
		// TODO Auto-generated method stub
		List<Student> students = (List<Student>) studentRepository.findAll();
		return students.stream().map(StudentDTO::new).collect(Collectors.toList());
	}


	@Override
	public Student getStudentById(int id) {
		// TODO Auto-generated method stub
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Student not found"));
		return student;
		
	}


	@Override
	public void softDeleteById(int id) {
		// TODO Auto-generated method stub
		Student student = studentRepository.findById(id)
				 .orElseThrow(() -> new EntityNotFoundException("Student not found"));

	    student.setDeleted(true);
	    studentRepository.save(student);
	}


	@Override
	public void restoreById(int id) {
		// TODO Auto-generated method stub
		Student student = studentRepository.findById(id)
				 .orElseThrow(() -> new EntityNotFoundException("Student not found"));

	    student.setDeleted(false);
	    studentRepository.save(student);
	}


	@Override
	public Student createStudent(StudentRequest request) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(request.getUserId()).orElse(null);
		user.setRole("STUDENT");
		Student student = new Student();
		student.setEnroll_date(request.getEnroll_date());
		student.setUser(user);
		userRepository.save(user);
		studentRepository.save(student);
		return student;
	}


	@Override
	public Student updateStudent(int id, StudentUpdateRequest request) {
	    Optional<Student> optionalStudent = studentRepository.findById(id);
//	    Optional<User> optionalUser = userRepository.findById(request.getUserId());
	    

	    if (!optionalStudent.isPresent()) {
	        throw new RuntimeException("Student not found with ID: " + id);
	    }

	    User user = optionalStudent.get().getUser();
	    user.setName(request.getName());
	    userRepository.save(user);

	    Student student = optionalStudent.get();

	    // If enrollDate is a String in the request
	    student.setEnroll_date(request.getEnrollDate());

	    // OR if it's already a java.sql.Date:
	    // student.setEnrollDate(request.getEnrollDate());

	    return studentRepository.save(student);
	}


}
