package com.lms.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.backend.dto.StudentScoreDTO;
import com.lms.backend.repository.StudentExamResultRepository;
import com.lms.backend.service.StudentExamResultService;

@Service
public class StudentExamResultServiceImpl implements StudentExamResultService {

    @Autowired
    private StudentExamResultRepository resultRepository;

    @Override
    public List<StudentScoreDTO> getResultsByExamId(Integer examId) {
        return resultRepository.findResultsByExamId(examId);
    }
}

