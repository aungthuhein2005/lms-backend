package com.lms.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lms.backend.dto.AuthRequest;
import com.lms.backend.dto.AuthResponse;
import com.lms.backend.dto.RegisterRequest;
import com.lms.backend.entity.User;
import com.lms.backend.repository.UserRepository;
import com.lms.backend.security.JwtService;
import com.lms.backend.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;

	@Override
	public AuthResponse register(RegisterRequest request) {
		// TODO Auto-generated method stub
		if(userRepository.existsByEmail(request.getEmail())){
			throw new RuntimeException("Email arelady in use");
		}
		
		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setAddress(request.getAddress());
		user.setPhone(request.getPhone());
		user.setGender(request.getGender());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		userRepository.save(user);
		String token = jwtService.generateToken(user);
		
		return new AuthResponse(token,user);
	}

	@Override
	public AuthResponse login(AuthRequest request) {
		// TODO Auto-generated method stub
		User user  = userRepository.findByEmail(request.getEmail())
				.orElseThrow(()-> new RuntimeException("User not found"));
		if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid Credentials");
		}
		
		String token = jwtService.generateToken(user);
	System.out.print(user);
		return new AuthResponse(token,user);
	}

	@Override
	public void logout(String token) {
		// TODO Auto-generated method stub
//		tokenblac
	}

}
