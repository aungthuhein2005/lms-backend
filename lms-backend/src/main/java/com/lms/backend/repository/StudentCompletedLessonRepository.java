package com.lms.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lms.backend.entity.StudentCompletedLesson;

public interface StudentCompletedLessonRepository extends JpaRepository<StudentCompletedLesson, Long> {
    long countByStudentIdAndLessonIdIn(Long studentId, List<Integer> allLessonIds);
    boolean existsByStudentIdAndLessonId(Long studentId, Long lessonId);
    @Query("SELECT COUNT(scl) FROM StudentCompletedLesson scl WHERE scl.studentId = :studentId AND scl.lessonId IN :lessonIds")
    long countCompletedLessonsByStudent(@Param("studentId") int studentId, @Param("lessonIds") List<Long> lessonIds);

}
