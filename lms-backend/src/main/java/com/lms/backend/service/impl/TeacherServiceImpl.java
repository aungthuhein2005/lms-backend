package com.lms.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.backend.dto.TeacherRequest;
import com.lms.backend.dto.TeacherResponse;
import com.lms.backend.entity.Teacher;
import com.lms.backend.entity.User;
import com.lms.backend.repository.TeacherRepository;
import com.lms.backend.repository.UserRepository;
import com.lms.backend.service.TeacherService;
import com.lms.backend.service.UserService;

@Service
public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public List<TeacherResponse> getAllTeachers() {
	    List<Teacher> teachers = (List<Teacher>) teacherRepository.findAll();
	    return teachers.stream().map(TeacherResponse::new).collect(Collectors.toList()); 
	}


	@Override
	public Teacher createTeacher(TeacherRequest teacherRequest) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(teacherRequest.getUserId()).orElse(null);
		Teacher teacher = new Teacher();
		teacher.setHire_date(teacherRequest.getHireDate());
		teacher.setUser(user);
		return teacher;
	}

	@Override
	public void deleteTeacher(int id) {
		// TODO Auto-generated method stub
		teacherRepository.deleteById(id);
	}

	@Override
	public Teacher getTeacherById(int id) {
		// TODO Auto-generated method stub
		return teacherRepository.findById(id).orElse(null);
	}
	
}
