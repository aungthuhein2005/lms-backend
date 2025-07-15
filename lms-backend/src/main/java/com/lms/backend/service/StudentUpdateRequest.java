package com.lms.backend.service;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lms.backend.entity.Gender;

import lombok.Data;

@Data
public class StudentUpdateRequest {
	private String name;
	private String email;
	private String address;
	private LocalDate dob;
	private Gender gender;
	private String phone;
	private String profile;
	private Date enrollDate;
}
