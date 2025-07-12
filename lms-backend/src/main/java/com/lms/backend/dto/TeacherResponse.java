package com.lms.backend.dto;

import java.time.LocalDate;
import java.util.Date;

import com.lms.backend.entity.Teacher;

import lombok.Data;

@Data
public class TeacherResponse {
	private int id;
	private String name;
	private LocalDate hireDate;
	private boolean deleted;
	
	public TeacherResponse(Teacher teacher) {
		this.id = teacher.getId();
		this.name = teacher.getUser().getName();
		this.hireDate = teacher.getHire_date();
		this.deleted = teacher.isDeleted();
	}
}
