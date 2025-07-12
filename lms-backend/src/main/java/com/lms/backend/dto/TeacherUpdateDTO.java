package com.lms.backend.dto;

import java.time.LocalDate;

import com.lms.backend.entity.Gender;

import lombok.Data;

@Data
public class TeacherUpdateDTO {

	private String name;
	private String email;
	private String address;
	private LocalDate dob;
	private Gender gender;
	private String phone;
	private String profile;
	private LocalDate hireDate;
	
	
}
