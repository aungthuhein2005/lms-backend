package com.lms.backend.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.backend.dto.TeacherRequest;
import com.lms.backend.dto.TeacherResponse;
import com.lms.backend.dto.TeacherUpdateDTO;
import com.lms.backend.entity.ClassEntity;
import com.lms.backend.entity.ClassSchedule;
import com.lms.backend.entity.Teacher;
import com.lms.backend.entity.User;
import com.lms.backend.repository.ClassRepository;
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
	private ClassRepository classRepository;
	
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
		teacherRepository.save(teacher);
		return teacher;
	}
	
	public boolean existsById(Integer id) {
	    return teacherRepository.existsById(id);
	}

	public Teacher teacherHire(Integer id, TeacherRequest request) {
	    Teacher teacher = teacherRepository.findById(id).orElseThrow();
	    Optional<User> optionalUser = userRepository.findById(teacher.getUser().getId());
	    User user = optionalUser.get();
	    user.setName(request.getName());
//	    user.setAddress(request.get);
	    teacher.setHire_date(request.getHireDate());
	    return teacherRepository.save(teacher);
	}
	
	public Teacher updateTeacher(Integer id, TeacherUpdateDTO request) {
	    Teacher teacher = teacherRepository.findById(id).orElseThrow();
	    Optional<User> optionalUser = userRepository.findById(teacher.getUser().getId());
	    User user = optionalUser.get();
	    user.setName(request.getName());
	    user.setAddress(request.getAddress());
	    user.setEmail(request.getEmail());
	    user.setDob(request.getDob());
	    user.setGender(request.getGender());
	    user.setPhone(request.getPhone());
	    user.setProfile(request.getProfile());
	    teacher.setHire_date(request.getHireDate());
	    userRepository.save(user);
	    return teacherRepository.save(teacher);
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
	
	public void assignToClass(Integer teacherId, Integer classId, String assignedAt) {
	    Teacher teacher = teacherRepository.findById(teacherId)
	            .orElseThrow(() -> new RuntimeException("Teacher not found"));

	    ClassEntity classEntity = classRepository.findById(classId)
	            .orElseThrow(() -> new RuntimeException("Class not found"));
	    classEntity.setTeacher(teacher);
	    classRepository.save(classEntity);
	    
	}


	@Override
	public List<ClassEntity> getClassByTeacherId(int id) {
		// TODO Auto-generated method stub
		List<ClassEntity> classEntities = classRepository.findAllByTeacherId(id);
		return classEntities;
	}


	@Override
	public List<ClassSchedule> getClassSchedulesById(int id) {
		 List<ClassEntity> classes = classRepository.findByTeacherId(id);
		    return classes.stream()
		        .flatMap(cls -> cls.getSchedules().stream())
		        .collect(Collectors.toList());
	}

	
}
