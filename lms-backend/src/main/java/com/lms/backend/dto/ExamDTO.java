package com.lms.backend.dto;

import java.util.List;
import lombok.Data;
@Data
public class ExamDTO {

	private int id;
	private String title;
	private String description;
	private Integer duration;
	private int score;
	private List<QuestionDTO> questions;
	
	private Integer classId; 
	 private String className;
}