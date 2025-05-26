package com.lms.backend.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class AcademicYearDetailResponse {
    private Integer id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<SemesterResponse> semesters;

    @Data
    public static class SemesterResponse {
        private Integer id;
        private String name;
        private LocalDate startDate;
        private LocalDate endDate;
    }
}
