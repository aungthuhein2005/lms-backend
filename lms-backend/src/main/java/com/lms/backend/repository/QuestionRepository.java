package com.lms.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.lms.backend.entity.Question;

public interface QuestionRepository extends CrudRepository<Question, Integer>{

}