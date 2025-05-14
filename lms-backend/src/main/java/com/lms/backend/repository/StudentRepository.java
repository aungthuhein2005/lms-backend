package com.lms.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.lms.backend.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Integer>{

}
