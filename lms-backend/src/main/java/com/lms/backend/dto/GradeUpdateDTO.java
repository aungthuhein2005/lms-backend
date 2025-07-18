package com.lms.backend.dto;

import lombok.Data;

@Data
public class GradeUpdateDTO {
	private int studentId;
	private int examId;
	private String grade;
}
