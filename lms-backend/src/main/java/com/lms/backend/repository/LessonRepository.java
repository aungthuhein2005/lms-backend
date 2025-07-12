package com.lms.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lms.backend.entity.Lesson;
public interface LessonRepository extends CrudRepository<Lesson, Integer> {
	List<Lesson> findByModule_IdIn(List<Integer> moduleIds);
    List<Lesson> findByModuleId(Integer id);
}

