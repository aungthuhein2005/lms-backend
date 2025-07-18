package com.lms.backend.dto;

import lombok.Data;

@Data
public class TeacherCourseProgressDTO {
    private int courseId;
    private String courseTitle;
    private double averageProgress;
    private int studentCount;
}
