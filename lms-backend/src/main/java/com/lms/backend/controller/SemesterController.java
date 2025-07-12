package com.lms.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lms.backend.dto.SemesterRequest;
import com.lms.backend.entity.Semester;
import com.lms.backend.service.SemesterService;
import com.lms.backend.service.impl.SemesterServiceImpl;

@RestController
@RequestMapping("/semesters")
public class SemesterController {

    private final SemesterServiceImpl semesterServiceImpl;

    public SemesterController(SemesterServiceImpl semesterService) {
        this.semesterServiceImpl = semesterService;
    }

    // Get all semesters
    @GetMapping
    public ResponseEntity<List<Semester>> getAllSemesters() {
        List<Semester> semesters = semesterServiceImpl.getAllSemesters();
        return ResponseEntity.ok(semesters);
    }
    

    // Get semester by id
    @GetMapping("/{id}")
    public ResponseEntity<Semester> getSemesterById(@PathVariable int id) {
        Semester semester = semesterServiceImpl.getSemesterById(id);
        return ResponseEntity.ok(semester);
    }
    
    @GetMapping("/academic_year/{yearId}")
    public ResponseEntity<?> getSemestersByAcademicYear(@PathVariable int yearId){
    	List<Semester> semesters = semesterServiceImpl.getSemestersByAcademicYear(yearId);
    	return ResponseEntity.ok(semesters);
    }

    // Create new semester
    @PostMapping
    public ResponseEntity<Semester> createSemester(@RequestBody SemesterRequest request) {
        // Assuming you add a create method in your service
        Semester created = semesterServiceImpl.createSemester(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Semester> updateSemester(@PathVariable int id, @RequestBody SemesterRequest request) {
//        request.setId(id); // Ensure the ID in request is set
        Semester updated = semesterServiceImpl.updateSemester(id, request);
        return ResponseEntity.ok(updated);
    }

    // Delete semester by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSemester(@PathVariable int id) {
        semesterServiceImpl.deleteSemester(id);
        return ResponseEntity.noContent().build();
    }
}
