package com.lms.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmittedAnswerDTO {

	  private int questionId;
	  private String questionText;
	    private String correctAnswer;
	    private String selectedOption;
}
	