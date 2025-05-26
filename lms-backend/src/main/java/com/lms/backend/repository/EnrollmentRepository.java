package com.lms.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lms.backend.entity.ClassEntity;
import com.lms.backend.entity.Enrollment;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Integer> {
	@Query("SELECT sc.classEntity FROM Enrollment sc WHERE sc.student.id = :studentId")
	List<ClassEntity> findClassesByStudentId(@Param("studentId") int studentId);


}
