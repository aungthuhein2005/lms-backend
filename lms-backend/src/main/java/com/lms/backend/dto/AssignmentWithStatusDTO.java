package com.lms.backend.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AssignmentWithStatusDTO {
    private Integer id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Integer point;
    private String media;
    private String status;

    private String teacher;
    private String classname;
}
