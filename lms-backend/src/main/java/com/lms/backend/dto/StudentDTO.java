package com.lms.backend.dto;

import java.util.Date;

import com.lms.backend.entity.Student;

import lombok.Data;

@Data
public class StudentDTO {
    private int id;
    private String name;
    private Date enrollDate;
    private boolean deleted;

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.name = student.getUser().getName();
        this.enrollDate = student.getEnroll_date();
        this.deleted = student.isDeleted();
    }

}
