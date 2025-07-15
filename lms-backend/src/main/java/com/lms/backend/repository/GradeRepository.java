package com.lms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lms.backend.entity.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {
    // Optionally add methods, e.g., findByStudentId, findByExamId
}
