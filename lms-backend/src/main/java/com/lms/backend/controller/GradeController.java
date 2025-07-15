package com.lms.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lms.backend.dto.GradeDTO;
import com.lms.backend.service.GradeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @PostMapping
    public ResponseEntity<GradeDTO> createGrade(@RequestBody GradeDTO dto) {
        return new ResponseEntity<>(gradeService.createGrade(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradeDTO> getGrade(@PathVariable int id) {
        return ResponseEntity.ok(gradeService.getGradeById(id));
    }

    @GetMapping
    public ResponseEntity<List<GradeDTO>> getAllGrades() {
        return ResponseEntity.ok(gradeService.getAllGrades());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GradeDTO> updateGrade(@PathVariable int id, @RequestBody GradeDTO dto) {
        return ResponseEntity.ok(gradeService.updateGrade(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable int id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }
}
