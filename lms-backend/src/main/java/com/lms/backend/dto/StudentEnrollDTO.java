package com.lms.backend.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class StudentEnrollDTO {
	private String name;
	 @JsonFormat(pattern = "yyyy-MM-dd")
	private Date enrollDate;
}
