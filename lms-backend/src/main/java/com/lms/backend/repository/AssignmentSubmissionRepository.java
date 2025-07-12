package com.lms.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.backend.entity.Assignment;
import com.lms.backend.entity.AssignmentSubmission;


public interface AssignmentSubmissionRepository extends JpaRepository<AssignmentSubmission, Integer> {
    List<AssignmentSubmission> findByAssignmentId(Integer assignmentId);
}
