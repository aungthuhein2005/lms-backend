package com.lms.backend.service;

import java.util.List;

import com.lms.backend.dto.StudentDTO;
import com.lms.backend.dto.StudentRequest;
import com.lms.backend.entity.ClassEntity;
import com.lms.backend.entity.Student;

public interface StudentService {

	List<StudentDTO> getAllStudents();
	Student getStudentById(int id);
	void softDeleteById(int id);
	void restoreById(int id);
	Student createStudent(StudentRequest request);
	Student updateStudent(int id,StudentUpdateRequest request);
	
}
