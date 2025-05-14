package com.lms.backend.entity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.lms.backend.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	List<User> findByName(String name);
//	List<User> findByEmail(String email);
	Optional<User> findByEmail(String eamil);//Find user by email
}
