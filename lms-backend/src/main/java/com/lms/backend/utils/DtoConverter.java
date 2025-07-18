package com.lms.backend.utils;

import com.lms.backend.dto.StudentDTO;
import com.lms.backend.entity.Student;

public class DtoConverter {

	public static StudentDTO convertToStudentDTO(Student student) {
		return new StudentDTO(student);
	}
}
