package com.lms.backend.dto;

import lombok.Data;

@Data
public class StudentCoursePorgressDTO {
	 private int courseId;
	    private String courseTitle;
	    private double progressPercent;
	    private boolean completed;
}
