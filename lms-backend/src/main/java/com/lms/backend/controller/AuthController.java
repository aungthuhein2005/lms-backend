package com.lms.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.backend.dto.AuthRequest;
import com.lms.backend.dto.AuthResponse;
import com.lms.backend.dto.RegisterRequest;
import com.lms.backend.service.impl.AuthServiceImpl;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
	
	@Autowired
	private AuthServiceImpl authServiceImpl;
	
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
		AuthResponse authResponse = authServiceImpl.register(request);
		return ResponseEntity.ok(authResponse);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
		AuthResponse authResponse = authServiceImpl.login(request);
		return ResponseEntity.ok(authResponse);
	}
	
}
