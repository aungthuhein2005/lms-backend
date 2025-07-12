package com.lms.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lms.backend.entity.Module;

@Repository
public interface ModuleRepository extends CrudRepository<Module, Integer> {
	List<Module> findByCourse_IdIn(List<Integer> courseIds);

    List<Module> findByCourseId(Integer id);
}

