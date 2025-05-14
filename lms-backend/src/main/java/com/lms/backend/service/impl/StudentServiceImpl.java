package com.lms.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lms.backend.dto.StudentDTO;
import com.lms.backend.entity.Student;
import com.lms.backend.repository.StudentRepository;
import com.lms.backend.service.StudentService;
import com.lms.backend.utils.DtoConverter;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentServiceImpl implements StudentService{

	private final StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	
	@Override
	public List<StudentDTO> getAllStudents() {
		// TODO Auto-generated method stub
		List<Student> students = (List<Student>) studentRepository.findAll();
		return students.stream().map(StudentDTO::new).collect(Collectors.toList());
	}


	@Override
	public StudentDTO getStudentById(int id) {
		// TODO Auto-generated method stub
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Student not found"));
		return DtoConverter.convertToStudentDTO(student);
		
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

}
