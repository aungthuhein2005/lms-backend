package com.lms.backend.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SemesterRequest {
    private Integer id;                  // For update scenarios
    private String name;                // e.g., "Semester 1", "Fall 2024"
    private LocalDate startDate;        // Start of the semester
    private LocalDate endDate;          // End of the semester
    private Integer academicYearId;     // Foreign key reference to AcademicYear
}
