package com.lms.backend.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.lms.backend.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

	Optional<Course> findByTitle(String title);
	List<Course> findAllByIdIn(Set<Integer> ids);

}
