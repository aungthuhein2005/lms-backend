package com.lms.backend.service;

import java.util.List;
import com.lms.backend.dto.ExamDTO;


public interface ExamService {

  List<ExamDTO> getAllExams();
  ExamDTO getExamById(Integer id);
  ExamDTO createExam(ExamDTO examDTO);
  ExamDTO updateExam(Integer id,ExamDTO examDTO);
  void deleteExam(Integer id);
}