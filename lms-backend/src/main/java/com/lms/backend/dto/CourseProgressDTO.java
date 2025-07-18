package com.lms.backend.dto;

import lombok.Data;

@Data
public class CourseProgressDTO {
    private int classId;
    private int studentId;
    private String studentName;
    private double progressPercent;
}
