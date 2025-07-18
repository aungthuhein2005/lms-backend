package com.lms.backend.dto;

import lombok.Data;

@Data
public class LessonCompleteRequest {
    private Long studentId;
    private Long lessonId;
}
