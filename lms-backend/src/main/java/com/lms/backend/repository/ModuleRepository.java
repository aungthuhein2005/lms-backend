package com.lms.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.lms.backend.entity.Module;

public interface ModuleRepository extends CrudRepository<Module,Integer>{
	List<Module> findByCourseId(Integer courseId);
	

}
