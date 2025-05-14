package com.lms.backend.dto;

import com.lms.backend.entity.Student;

import lombok.Data;

@Data
public class StudentDTO {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String gender;
    private boolean deleted;

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.name = student.getUser().getName();
        this.address = student.getUser().getAddress();
        this.phone = student.getUser().getPhone();
        this.email = student.getUser().getEmail();
        this.gender = student.getUser().getGender().toString();
        this.deleted = student.isDeleted();
    }

    // Getters and setters (or use Lombok's @Data)
}
