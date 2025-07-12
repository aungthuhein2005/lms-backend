package com.lms.backend.controller;

import java.util.List; 
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.backend.dto.AssignRequest;
import com.lms.backend.dto.TeacherRequest;
import com.lms.backend.dto.TeacherResponse;
import com.lms.backend.dto.TeacherUpdateDTO;
import com.lms.backend.entity.ClassEntity;
import com.lms.backend.entity.ClassSchedule;
import com.lms.backend.entity.Teacher;
import com.lms.backend.service.ClassService;
import com.lms.backend.service.UserService;
import com.lms.backend.service.impl.TeacherServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TeacherController {

	private final TeacherServiceImpl teacherServiceImpl;
	private final UserService userService;

	@GetMapping
	public ResponseEntity<List<TeacherResponse>> getAllTeachers() {
		return ResponseEntity.ok(teacherServiceImpl.getAllTeachers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getTeacherById(@PathVariable Integer id) {
		Teacher teacher = teacherServiceImpl.getTeacherById(id);
		List<ClassEntity> classEntity = teacherServiceImpl.getClassByTeacherId(id);
		if (teacher == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Teacher not found"));
		}
		return ResponseEntity.ok(Map.of(
		        "teacher", teacher,
		        "classes", classEntity != null ? classEntity : "No class assigned"
		    ));

	}

	@PostMapping
	public ResponseEntity<?> createTeacher(@RequestBody TeacherRequest request) {
		if (userService.hasUser(request.getUserId())) {
			Teacher createdTeacher = teacherServiceImpl.createTeacher(request);
			return ResponseEntity.ok(createdTeacher);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "User not found"));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTeacher(@PathVariable Integer id) {
		teacherServiceImpl.deleteTeacher(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/hire/{id}")
	public ResponseEntity<?> hireTeacher(@PathVariable Integer id, @RequestBody TeacherRequest request) {
		if (!teacherServiceImpl.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Teacher not found"));
		}

		try {
			Teacher updatedTeacher = teacherServiceImpl.teacherHire(id,request);
			return ResponseEntity.ok(updatedTeacher);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("message", "Error updating teacher", "error", e.getMessage()));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateTeacher(@PathVariable Integer id, @RequestBody TeacherUpdateDTO request) {
		if (!teacherServiceImpl.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Teacher not found"));
		}

		try {
			Teacher updatedTeacher = teacherServiceImpl.updateTeacher(id,request);
			return ResponseEntity.ok(updatedTeacher);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("message", "Error updating teacher", "error", e.getMessage()));
		}
	}

	@PostMapping("/assign_to_class")
	public ResponseEntity<?> assignToClass(@RequestBody AssignRequest request) {
		try {
			teacherServiceImpl.assignToClass(request.getTeacherId(), request.getClassId(), request.getAssigned_at());
			return ResponseEntity.ok(Map.of("message", "Teacher assigned to class successfully"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("message", "Failed to assign teacher", "error", e.getMessage()));
		}
	}
	
	@GetMapping("/teachers/{id}/timetable")
	public List<ClassSchedule> getTimetableByTeacherId(@PathVariable int id) {
	    return teacherServiceImpl.getClassSchedulesById(id);
	}


}