package com.lms.backend.dto;

import lombok.Data;

@Data
public class RecentExamDTO {
	private int examId;
	private String examTitle;
	private String examDescription;
	private int teacherId;
	private String teacherName;
}
