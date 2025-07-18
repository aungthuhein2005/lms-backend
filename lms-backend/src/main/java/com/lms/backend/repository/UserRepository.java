package com.lms.backend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.lms.backend.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);
}