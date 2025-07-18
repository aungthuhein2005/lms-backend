package com.lms.backend.service;

import java.util.List;

import com.lms.backend.dto.SemesterRequest;
import com.lms.backend.entity.Semester;

public interface SemesterService {
	List<Semester> getAllSemesters();
	Semester createSemester(SemesterRequest request);
	Semester getSemesterById(int id);
	void deleteSemester(int id);
	Semester updateSemester(int id,SemesterRequest request);
	List<Semester> getSemestersByAcademicYear(int yearId);
}
