package com.lms.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.backend.entity.StudentCourseKey;
import com.lms.backend.entity.StudentCourseStatus;

public interface StudentCourseStatusRepository extends JpaRepository<StudentCourseStatus, StudentCourseKey> {
    Optional<StudentCourseStatus> findById(StudentCourseKey key);
}

