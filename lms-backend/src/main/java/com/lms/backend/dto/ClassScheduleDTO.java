package com.lms.backend.dto;

import java.util.List;

import com.lms.backend.entity.ClassSchedule;

import lombok.Data;

@Data
public class ClassScheduleDTO {
	private int classId;
	private String className;
	private List<ClassSchedule> schedules;
}
