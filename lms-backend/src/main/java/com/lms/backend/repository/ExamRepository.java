package com.lms.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lms.backend.entity.Exam;

public interface ExamRepository extends CrudRepository<Exam, Integer>{
	 List<Exam> findByTeacherIdOrderByCreatedAtDesc(Integer teacherId);
	 List<Exam> findByClassesId(Integer classId);

}