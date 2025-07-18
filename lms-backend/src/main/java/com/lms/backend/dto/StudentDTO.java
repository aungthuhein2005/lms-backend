package com.lms.backend.dto;

import java.time.LocalDate;
import java.util.Date;

import com.lms.backend.entity.Student;

import lombok.Data;

@Data
public class StudentDTO {
    private int id;
    private String name;
    private Date enrollDate;
    private boolean deleted;
    private String email;

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.name = student.getUser().getName();
        this.enrollDate = student.getEnroll_date();
        this.deleted = student.isDeleted();
    }

	public StudentDTO(int id, String name, String email,Date enrollDate) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
		this.email = email;
		this.enrollDate = enrollDate;
	}
    

}
