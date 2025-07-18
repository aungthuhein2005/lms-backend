package com.lms.backend.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AcademicYearRequest {
	private String name;
	private LocalDate startDate;
	private LocalDate endDate;
}
