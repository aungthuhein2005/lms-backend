package com.lms.backend.repository;


import org.springframework.data.repository.CrudRepository;

import com.lms.backend.entity.Teacher;


public interface TeacherRepository extends CrudRepository<Teacher, Integer> {
  
}