package com.lms.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lms.backend.dto.StudentScoreDTO;
import com.lms.backend.service.StudentExamResultService;

@RestController
@RequestMapping("/results")
@CrossOrigin
public class StudentExamResultController {

    @Autowired
    private StudentExamResultService resultService;

    @GetMapping("/exam/{examId}")
    public List<StudentScoreDTO> getResultsByExamId(@PathVariable Integer examId) {
        return resultService.getResultsByExamId(examId);
    }
}

