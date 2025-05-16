package com.lms.backend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.lms.backend.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Integer>{

	Optional<Course> findByTitle(String title);

}
