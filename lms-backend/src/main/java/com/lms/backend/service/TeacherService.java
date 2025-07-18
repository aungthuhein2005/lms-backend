package com.lms.backend.service;

import java.util.List;

import com.lms.backend.dto.TeacherRequest;
import com.lms.backend.dto.TeacherResponse;
import com.lms.backend.entity.ClassEntity;
import com.lms.backend.entity.ClassSchedule;
import com.lms.backend.entity.Teacher;

public interface TeacherService {
	List<TeacherResponse> getAllTeachers();
	Teacher createTeacher(TeacherRequest teacherRequest);
	void deleteTeacher(int id);
	Teacher getTeacherById(int id);
	List<ClassEntity> getClassByTeacherId(int id);
	List<ClassSchedule> getClassSchedulesById(int id);
}
