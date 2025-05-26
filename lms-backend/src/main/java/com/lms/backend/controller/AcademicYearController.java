package com.lms.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lms.backend.dto.AcademicYearDetailResponse;
import com.lms.backend.dto.AcademicYearRequest;
import com.lms.backend.dto.AcademicYearResponse;
import com.lms.backend.entity.AcademicYear;
import com.lms.backend.service.AcademicYearService;

@RestController
@RequestMapping("/academic-years")
public class AcademicYearController {

    @Autowired
    private AcademicYearService academicYearService;

    @PostMapping
    public ResponseEntity<AcademicYear> create(@RequestBody AcademicYearRequest request) {
        AcademicYear year = academicYearService.createAcademicYear(request);
        return ResponseEntity.ok(year);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcademicYear> update(@PathVariable int id, @RequestBody AcademicYearRequest request) {
        
        AcademicYear updated = academicYearService.updateAcademicYear(id,request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @GetMapping
    public ResponseEntity<List<AcademicYearResponse>> getAll() {
        return ResponseEntity.ok(academicYearService.getAllAcademicYears());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcademicYearDetailResponse> getById(@PathVariable int id) {
        AcademicYearDetailResponse response = academicYearService.getAcademicYearById(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        academicYearService.deleteAcademicYear(id);
        return ResponseEntity.noContent().build();
    }
}
