package com.lms.backend.dto;

import lombok.Data;

@Data
public class CourseEnrollmentSummaryDTO {
	private int courseId;
	private String courseName;
	private int studentCount;
}
