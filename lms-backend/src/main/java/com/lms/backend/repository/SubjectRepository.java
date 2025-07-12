package com.lms.backend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.lms.backend.entity.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Integer> {

  Optional<Subject> findByName(String name);
  
}