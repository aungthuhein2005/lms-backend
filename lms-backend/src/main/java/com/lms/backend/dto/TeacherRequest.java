package com.lms.backend.dto;

import java.util.Date;

import lombok.Data;

@Data
public class TeacherRequest {
	
	private int userId;
	private Date hireDate; 

}
