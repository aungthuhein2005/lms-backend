package com.lms.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lms.backend.entity.Lesson;
public interface LessonRepository extends CrudRepository<Lesson, Long> {
	List<Lesson> findByModule_IdIn(List<Integer> moduleIds);
    List<Lesson> findByModuleId(Integer id);
    List<Lesson> findAllByModuleIdIn(List<Integer> moduleIds);
    @Query("SELECT l FROM Lesson l WHERE l.module.course.id = :courseId")
    List<Lesson> findByCourseId(@Param("courseId") int courseId);

}

