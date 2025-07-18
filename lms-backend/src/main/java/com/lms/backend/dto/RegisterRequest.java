package com.lms.backend.dto;

import com.lms.backend.entity.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RegisterRequest {

	private String name;
	private String email;
	private String address;
    private String phone;
	private String password;
	private Gender gender;
}
