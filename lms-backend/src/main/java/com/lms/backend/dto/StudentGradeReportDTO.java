package com.lms.backend.dto;

import lombok.Data;

@Data
public class StudentGradeReportDTO {
	private int studentId;
	private String studentName;
	private String className;
	private String subject;
	private String grade;
}	
