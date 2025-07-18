package com.lms.backend.dto;

import java.util.List;
import lombok.Data;

@Data
public class QuestionDTO {
	private int id;
	private String questionText;
	private int correcrtOption;
	private List<String> options;

}