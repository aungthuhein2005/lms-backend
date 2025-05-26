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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.backend.dto.TeacherRequest;
import com.lms.backend.dto.TeacherResponse;
import com.lms.backend.entity.Teacher;
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
  public ResponseEntity<List<TeacherResponse> > getAllTeachers(){
    return ResponseEntity.ok(teacherServiceImpl.getAllTeachers());
  }
      @GetMapping("/{id}")
      public ResponseEntity<?> getTeacherById(@PathVariable Integer id) {
          Teacher teacher = teacherServiceImpl.getTeacherById(id);
          if(teacher == null) {
        	  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Teacher not found"));
          }
          return ResponseEntity.ok(teacher);
      }

      @PostMapping
      public ResponseEntity<?> createTeacher(@RequestBody TeacherRequest request) {
          if(userService.hasUser(request.getUserId())) {
        	  Teacher createdTeacher = teacherServiceImpl.createTeacher(request);
              return ResponseEntity.ok(createdTeacher);
          }
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","User not found"));
      }

      @DeleteMapping("/{id}")
      public ResponseEntity<Void> deleteTeacher(@PathVariable Integer id) {
          teacherServiceImpl.deleteTeacher(id);
          return ResponseEntity.noContent().build();
      }

//      @PutMapping("/{id}")
//      public ResponseEntity<Teacher> updateTeacher(@PathVariable Integer id, @RequestBody Teacher teacher) {
//          Teacher updatedTeacher = teacherServiceImpl.createTeacher(teacher);
//          return ResponseEntity.ok(updatedTeacher);
//      }
}