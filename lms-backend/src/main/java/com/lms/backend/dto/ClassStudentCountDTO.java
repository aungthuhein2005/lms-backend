package com.lms.backend.dto;

import lombok.Data;

@Data
public class ClassStudentCountDTO {
    private String className;
    private int studentCount;

    public ClassStudentCountDTO(String className, int studentCount) {
        this.className = className;
        this.studentCount = studentCount;
    }

    // Getters and Setters
}
