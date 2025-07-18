package com.lms.backend.service;

import java.util.List;

import com.lms.backend.dto.StudentScoreDTO;

public interface StudentExamResultService {
    List<StudentScoreDTO> getResultsByExamId(Integer examId);

}
