package com.lms.backend.service;

import com.lms.backend.dto.AuthRequest;
import com.lms.backend.dto.AuthResponse;
import com.lms.backend.dto.RegisterRequest;

public interface AuthService {
	AuthResponse register(RegisterRequest request);
	AuthResponse login(AuthRequest request);
	void logout(String token);
}
