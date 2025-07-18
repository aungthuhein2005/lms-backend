package com.lms.backend.dto;

import lombok.Data;

@Data
public class StudentExamResultDTO {
    private String examName;
    private String grade;

    public StudentExamResultDTO(String examName, String grade) {
        this.examName = examName;
        this.grade = grade;
    }

    // Getters and setters
}
