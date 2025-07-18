package com.lms.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentScoreDTO {
	 private Integer studentId;
	    private Integer score;
}
