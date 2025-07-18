package com.lms.backend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.backend.entity.Assignment;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {

    List<Assignment> findByClassEntity_IdIn(List<Integer> classIds);
    List<Assignment> findByClassEntityId(int classId);
    List<Assignment> findByTeacherId(int teacherId);

    // This query is for filtering by class IDs
    @Query("SELECT a FROM Assignment a WHERE a.classEntity.id IN :classIds AND a.dueDate BETWEEN :now AND :threshold")
    List<Assignment> findNearDeadlineAssignmentsByClassIds(
                                                        @Param("classIds") List<Integer> classIds,
                                                        @Param("now") LocalDate now,
                                                        @Param("threshold") LocalDate threshold);

    // Corrected query for filtering by teacherId
    // Ensure that 'teacherId' is a direct field on the Assignment entity,
    // or adjust the path if it's nested (e.g., a.teacher.id)
    @Query("SELECT a FROM Assignment a WHERE a.teacher.id = :teacherId AND a.dueDate BETWEEN :now AND :threshold")
    List<Assignment> findNearDeadlineAssignmentsByTeacherId(
                                                            @Param("teacherId") Integer teacherId, // Changed 'teahcerId' to 'Integer teacherId' or 'int teacherId'
                                                            @Param("now") LocalDate now,
                                                            @Param("threshold") LocalDate threshold);

}