package com.lms.backend.service;

import java.util.List;

import com.lms.backend.dto.AnswerDTO;
import com.lms.backend.dto.ExamDTO;
import com.lms.backend.dto.ExamResultDTO;
import com.lms.backend.dto.RecentExamDTO;
import com.lms.backend.dto.StudentScoreDTO;
import com.lms.backend.entity.Exam;


public interface ExamService {

	List<ExamDTO> getAllExams();
	ExamDTO getExamById(Integer id);
	ExamDTO createExam(ExamDTO examDTO);
	ExamDTO updateExam(Integer id,ExamDTO examDTO);
	void deleteExam(Integer id);
	
	ExamResultDTO submitExamAnswers(Integer examId,Integer studentId,List<AnswerDTO> answers);
	ExamResultDTO getExamResultByExamId(Integer examId);
	List<StudentScoreDTO> getAttemptExamsById(Integer id);

    void updateExamGrade(Integer id,int studentId, String grade);
    List<RecentExamDTO> getRecentExamByTeacherId(int teacherId);
}