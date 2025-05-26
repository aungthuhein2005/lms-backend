package com.lms.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lms.backend.dto.SemesterRequest;
import com.lms.backend.entity.Semester;
import com.lms.backend.service.SemesterService;

@RestController
@RequestMapping("/semesters")
public class SemesterController {

    private final SemesterService semesterService;

    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    // Get all semesters
    @GetMapping
    public ResponseEntity<List<Semester>> getAllSemesters() {
        List<Semester> semesters = semesterService.getAllSemesters();
        return ResponseEntity.ok(semesters);
    }

    // Get semester by id
    @GetMapping("/{id}")
    public ResponseEntity<Semester> getSemesterById(@PathVariable int id) {
        Semester semester = semesterService.getSemesterById(id);
        return ResponseEntity.ok(semester);
    }

    // Create new semester
    @PostMapping
    public ResponseEntity<Semester> createSemester(@RequestBody SemesterRequest request) {
        // Assuming you add a create method in your service
        Semester created = semesterService.createSemester(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Semester> updateSemester(@PathVariable int id, @RequestBody SemesterRequest request) {
        request.setId(id); // Ensure the ID in request is set
        Semester updated = semesterService.updateSemester(request);
        return ResponseEntity.ok(updated);
    }

    // Delete semester by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSemester(@PathVariable int id) {
        semesterService.deleteSemester(id);
        return ResponseEntity.noContent().build();
    }
}
