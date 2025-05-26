package com.lms.backend.service;

import com.lms.backend.dto.UserRequest;
import com.lms.backend.entity.User;
import com.lms.backend.repository.StudentRepository;
import com.lms.backend.repository.UserRepository;

import jakarta.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	public List<User> getAllUser(){
		return(List<User>) userRepository.findAll();
	}
	
	public Optional<User> getUserById(Integer id){
		return userRepository.findById(id);
		
	}
	public User createUser(UserRequest userRequest) {
		User user = new User();
		String hashedPassword = passwordEncoder.encode(userRequest.getPassword());
		user.setName(userRequest.getName());
		user.setEmail(userRequest.getEmail());
		user.setPassword(hashedPassword);
		return userRepository.save(user);
	}
	
	 public boolean hasStudentReference(int userId) {
	        return studentRepository.existsByUserId(userId);
	    }
	 
	 public boolean hasUser(int userId) {
		 return userRepository.existsById(userId);
	 }
	
	 @Transactional
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}
	
	@Transactional
	 public void forceDeleteUserAndStudent(int userId) {
	        studentRepository.deleteByUserId(userId); // Custom method in StudentRepository
	        userRepository.deleteById(userId);
	    }
	
}