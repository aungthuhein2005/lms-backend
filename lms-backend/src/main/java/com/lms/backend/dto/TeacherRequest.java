package com.lms.backend.dto;

import java.time.LocalDate;
import java.util.Date;

import lombok.Data;

@Data
public class TeacherRequest {
	
	private String name;
	private int userId;
	private LocalDate hireDate; 
}
