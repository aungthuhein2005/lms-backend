package com.lms.backend.dto;

import lombok.Data;

@Data
public class AssignmentSubmissionDTO {
	private String title;
    private String description;
    private String dueDate;
    private String media;
    private Integer point;
    private Integer classId;
}
