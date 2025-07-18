package com.lms.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SemesterClassCountDTO {
    private int semesterId;
    private String semesterName;
    private long classCount;
}
