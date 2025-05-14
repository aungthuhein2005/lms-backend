package com.lms.backend.service;

import com.lms.backend.entity.User;
import com.lms.backend.entity.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	public List<User> getAllUser(){
		return(List<User>) userRepository.findAll();
	}
	
	public Optional<User> getUserById(Integer id){
		return userRepository.findById(id);
		
	}
	public User createUser(User user) {
		return userRepository.save(user);
		
	}
	
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
		
	}
	
	public List<User> getUserByName(String name){
		return userRepository.findByName(name);
		
	}
	
	public Optional<User> getUserByEmail(String email){
		return userRepository.findByEmail(email);
	}
}