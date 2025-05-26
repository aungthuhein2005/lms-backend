package com.lms.backend.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class StudentUpdateRequest {
//	private int userId;
	private String name;
	 @JsonFormat(pattern = "yyyy-MM-dd")
	private Date enrollDate;
}
