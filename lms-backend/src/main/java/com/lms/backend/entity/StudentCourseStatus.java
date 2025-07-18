package com.lms.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "student_course_status")
@Data
public class StudentCourseStatus {

    @EmbeddedId
    private StudentCourseKey id;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NOT_STARTED;

    private double progressPercent = 0;

    private LocalDateTime updatedAt = LocalDateTime.now();

    public enum Status {
        NOT_STARTED, PROGRESS, COMPLETED
    }

}
