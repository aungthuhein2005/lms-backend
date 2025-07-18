package com.lms.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.lms.backend.entity.Assignment;
import com.lms.backend.entity.AssignmentSubmission;


public interface AssignmentSubmissionRepository extends CrudRepository<AssignmentSubmission, Integer> {
	List<AssignmentSubmission> findByAssignment_Id(Integer assignmentId);
	List<AssignmentSubmission> findByStudent_id(Integer studentId);
}
