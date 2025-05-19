package com.lms.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.lms.backend.entity.CourseModule;

public interface ModuleRepository extends CrudRepository<CourseModule,Integer>{
	List<CourseModule> findByCourseId(Integer courseId);
	

}
