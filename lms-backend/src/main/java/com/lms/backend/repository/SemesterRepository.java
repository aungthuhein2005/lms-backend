package com.lms.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lms.backend.entity.Semester;

@Repository
public interface SemesterRepository extends CrudRepository<Semester, Integer>{

}
