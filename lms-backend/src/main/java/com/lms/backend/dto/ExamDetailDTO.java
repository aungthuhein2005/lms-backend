package com.lms.backend.dto;

import java.util.List;

public class ExamDetailDTO {
	private Integer id;
	private String title;
	private String description;
	private Integer duration;
	private Integer score;
	private List<StudentScoreDTO> studentScore;
}
