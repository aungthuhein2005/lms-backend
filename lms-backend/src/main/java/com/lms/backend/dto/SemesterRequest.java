package com.lms.backend.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SemesterRequest {
    private String name;                // e.g., "Semester 1", "Fall 2024"
    private LocalDate start_date;        // Start of the semester
    private LocalDate end_date;          // End of the semester
    private Integer academic_year_id;     // Foreign key reference to AcademicYear
}
