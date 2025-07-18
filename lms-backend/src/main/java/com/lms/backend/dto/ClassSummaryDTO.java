package com.lms.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassSummaryDTO {
	

	private int classId;
	private String className;
	private int studentCount;
	private int assignmentCount;
	private int examCount;
	private double classProgress;
	private double averageScore;
	
	public ClassSummaryDTO(int studentCount, int assignmentCount, int examCount, double classProgress) {
		// TODO Auto-generated constructor stub
		this.studentCount = studentCount;
		this.assignmentCount = assignmentCount;
		this.examCount = examCount;
		this.classProgress = classProgress;
	}

	public ClassSummaryDTO(Integer id, String name, double progress) {
		// TODO Auto-generated constructor stub
		this.classId = id;
		this.className = name;
		this.classProgress = progress;
	}
}
