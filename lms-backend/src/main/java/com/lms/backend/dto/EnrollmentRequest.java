package com.lms.backend.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EnrollmentRequest {

	private int studentId;
	private int classId;
	private LocalDate enrolled_at;
	
}
