package com.lms.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.lms.backend.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}