package com.lms.backend.dto;

import lombok.Data;

@Data
public class AssignmentWithStatusDTO {
    private Integer id;
    private String title;
    private String description;
    private String dueDate;
    private Integer point;
    private String media;
    private String status;

    private String teacher;
    private String classname;
}
