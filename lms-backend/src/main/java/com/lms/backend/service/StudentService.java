package com.lms.backend.service;

import java.util.List;

import com.lms.backend.dto.StudentDTO;

public interface StudentService {

	List<StudentDTO> getAllStudents();
	StudentDTO getStudentById(int id);
	void softDeleteById(int id);
	void restoreById(int id);
	
}
