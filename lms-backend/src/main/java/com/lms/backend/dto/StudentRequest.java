package com.lms.backend.dto;

import java.util.Date;

import lombok.Data;

@Data
public class StudentRequest {
	private int userId;
	private Date enroll_date;
}
