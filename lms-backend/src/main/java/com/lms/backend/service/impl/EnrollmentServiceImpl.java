package com.lms.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.backend.dto.EnrollmentRequest;
import com.lms.backend.entity.ClassEntity;
import com.lms.backend.entity.Student;
import com.lms.backend.entity.Enrollment;
import com.lms.backend.entity.Semester;
import com.lms.backend.repository.ClassRepository;
import com.lms.backend.repository.EnrollmentRepository;
import com.lms.backend.repository.SemesterRepository;
import com.lms.backend.repository.StudentRepository;
import com.lms.backend.service.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService{
	
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	@Autowired
	private SemesterRepository semesterRepository;



	@Override
	public void assignStudent(EnrollmentRequest request) {
		// TODO Auto-generated method stub
		Student student = studentRepository.findById(request.getStudentId())
		        .orElseThrow(() -> new RuntimeException("Student not found with id " + request.getStudentId()));

		    ClassEntity cls = classRepository.findById(request.getClassId())
		        .orElseThrow(() -> new RuntimeException("Class not found with id " + request.getClassId()));
		    Enrollment enrollment = new Enrollment();
		    enrollment.setStudent(student);
		    enrollment.setClassEntity(cls);
		    enrollment.setEnrollmentDate(request.getEnrolled_at());

		    enrollmentRepository.save(enrollment);
	}



	@Override
	public List<ClassEntity> getAssignedClassesByStudentId(int studentId) {
		// TODO Auto-generated method stub
		List<ClassEntity> studentClasses = enrollmentRepository.findClassesByStudentId(studentId);
		return studentClasses;
	}

}
