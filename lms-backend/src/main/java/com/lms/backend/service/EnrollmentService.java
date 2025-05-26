package com.lms.backend.service;

import java.util.List;

import com.lms.backend.dto.EnrollmentRequest;
import com.lms.backend.entity.ClassEntity;

public interface EnrollmentService {
	void assignStudent(EnrollmentRequest request);
	List<ClassEntity> getAssignedClassesByStudentId(int studentId);
}
