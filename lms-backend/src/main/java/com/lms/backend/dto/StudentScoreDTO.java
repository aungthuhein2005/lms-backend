package com.lms.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentScoreDTO {
	 	private Integer studentId;
	 	private String studentName; 
	    private int score;
	    private String grade;
	    
	    public StudentScoreDTO(Integer studentId, int score) {
	        this.studentId = studentId;
	        this.score = score;
	    }
}