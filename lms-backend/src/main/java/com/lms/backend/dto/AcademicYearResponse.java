package com.lms.backend.dto;

import java.time.LocalDate;

import com.lms.backend.entity.AcademicYear;

import lombok.Data;

@Data
public class AcademicYearResponse {
	private int id;
	private String name;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public AcademicYearResponse(AcademicYear academicYear) {
		this.id = academicYear.getId();
		this.name = academicYear.getName();
		this.startDate = academicYear.getStartDate();
		this.endDate = academicYear.getEndDate();
	}
	

}
