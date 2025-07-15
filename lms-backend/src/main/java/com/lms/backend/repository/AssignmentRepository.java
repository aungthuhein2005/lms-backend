package com.lms.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.backend.entity.Assignment;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
	List<Assignment> findByClassEntity_IdIn(List<Integer> classIds);
	List<Assignment> findByTeacherId(int teacherId);
}