package com.lms.backend.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GradeDTO {
    private int id;
    private int examId;
    private int studentId;
    private String grade;
    private String gradeType;
    private LocalDateTime createdAt;

    // Getters and Setters or use Lombok @Data
}
