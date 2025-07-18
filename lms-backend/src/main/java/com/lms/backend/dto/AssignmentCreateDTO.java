package com.lms.backend.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AssignmentCreateDTO {
	private String title;
    private String description;
    private LocalDate dueDate;
    private String media;
    private Integer point;
    private Integer classId;
    private Integer teacherId;
}
