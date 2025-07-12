package com.lms.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import com.lms.backend.dto.ExamDTO;
import com.lms.backend.dto.QuestionDTO;
import com.lms.backend.entity.Exam;
import com.lms.backend.entity.Question;
import com.lms.backend.repository.ExamRepository;
import com.lms.backend.service.ExamService;

@Service
public class ExamServiceImpl implements ExamService{

  private ExamRepository examRepository;

      public ExamServiceImpl(ExamRepository examRepository) {
          this.examRepository = examRepository;
      }

    @Override
    public List<ExamDTO> getAllExams() {
      // TODO Auto-generated method stub
      List<ExamDTO> exams = new ArrayList<>();
      for(Exam e: examRepository.findAll()) {
        ExamDTO exam = new ExamDTO();
        exam.setId(e.getId());
        exam.setTitle(e.getTitle());
        exam.setDescription(e.getDescription());
        exam.setDuration(e.getDuration());
        exam.setScore(e.getScore());
        
        List<QuestionDTO> questions = new ArrayList<>();
        for(Question q:e.getQuestions()) {
          QuestionDTO question = new QuestionDTO();
          question.setId(q.getId());
          question.setQuestionText(q.getQuestionText());
          question.setCorrectAnswer(q.getCorrectAnswer());
          question.setOptions(q.getOptions());
          questions.add(question);
        }
        exam.setQuestions(questions);
        exams.add(exam);
      }
      return exams;
    }

    @Override
    public ExamDTO getExamById(Integer id) {
      // TODO Auto-generated method stub
      Exam e = examRepository.findById(id).orElseThrow(()->new NoSuchElementException("Exam not found"));
      
      ExamDTO exam = new ExamDTO();
      exam.setId(e.getId());
      exam.setTitle(e.getTitle());
      exam.setDescription(e.getDescription());
      exam.setDuration(e.getDuration());
      exam.setScore(e.getScore());
      
      List<QuestionDTO> questions = new ArrayList<>();
      for(Question q: e.getQuestions()) {
        QuestionDTO question = new QuestionDTO();
        question.setId(q.getId());
        question.setQuestionText(q.getQuestionText());
        question.setCorrectAnswer(q.getCorrectAnswer());
        question.setOptions(q.getOptions());
        questions.add(question);
      }
      exam.setQuestions(questions);
      
      return exam;
    }

    @Override
    public ExamDTO createExam(ExamDTO examDTO) {
      // TODO Auto-generated method stub
      Exam exam = new Exam();
      exam.setTitle(examDTO.getTitle());
      exam.setDescription(examDTO.getDescription());
      exam.setDuration(examDTO.getDuration());
      exam.setScore(examDTO.getScore());
      
      List<Question> questions = new ArrayList<>();
      if(examDTO.getQuestions() != null) {
        for(QuestionDTO qDTO: examDTO.getQuestions()) {
          Question q = new Question();
          q.setQuestionText(qDTO.getQuestionText());
          q.setCorrectAnswer(qDTO.getCorrectAnswer());
          q.setOptions(qDTO.getOptions());
          q.setExam(exam);
          questions.add(q);
        }
      }
      
      exam.setQuestions(questions);
      
      Exam saved = examRepository.save(exam);
      
      ExamDTO examdto = new ExamDTO();
      examdto.setId(saved.getId());
      examdto.setTitle(saved.getTitle());
      examdto.setDescription(saved.getDescription());
      examdto.setDuration(saved.getDuration());
      examdto.setScore(saved.getScore());
      
      List<QuestionDTO> questionsDto = new ArrayList<>();
      for(Question q : saved.getQuestions()) {
        QuestionDTO qDto =  new QuestionDTO();
        qDto.setId(q.getId());
        qDto.setQuestionText(q.getQuestionText());
        qDto.setCorrectAnswer(q.getCorrectAnswer());
        qDto.setOptions(q.getOptions());
        questionsDto.add(qDto);
      }
      examdto.setQuestions(questionsDto);
      return examdto;
    }

    @Override
    public ExamDTO updateExam(Integer id, ExamDTO examDTO) {
      // TODO Auto-generated method stub
      Exam exam = examRepository.findById(id).orElseThrow(()->new NoSuchElementException("Exam not found"));
      exam.setTitle(examDTO.getTitle());
      exam.setDescription(examDTO.getDescription());
      exam.setDuration(examDTO.getDuration());
      exam.setScore(examDTO.getScore());
      
      List<Question> updateQuestions = new ArrayList<>();
      if(examDTO.getQuestions() != null) {
        for(QuestionDTO qDTO : examDTO.getQuestions()) {
          Question q = new Question();
          q.setId(qDTO.getId());
          q.setQuestionText(qDTO.getQuestionText());
          q.setCorrectAnswer(qDTO.getCorrectAnswer());
          q.setOptions(qDTO.getOptions());
          q.setExam(exam);
          updateQuestions.add(q);
        }
      }
      exam.setQuestions(updateQuestions);
      Exam updated = examRepository.save(exam);
      
      ExamDTO examdto = new ExamDTO();
      examdto.setId(updated.getId());
      examdto.setTitle(updated.getTitle());
      examdto.setDescription(updated.getDescription());
      examdto.setDuration(updated.getDuration());
      examdto.setScore(updated.getScore());
      
      List<QuestionDTO> questionsDto = new ArrayList<>();
      for(Question q : updated.getQuestions()) {
        QuestionDTO qDto =  new QuestionDTO();
        qDto.setId(q.getId());
        qDto.setQuestionText(q.getQuestionText());
        qDto.setCorrectAnswer(q.getCorrectAnswer());
        qDto.setOptions(q.getOptions());
        questionsDto.add(qDto);
      }
      examdto.setQuestions(questionsDto);
      
      return examdto;
    }

    @Override
    public void deleteExam(Integer id) {
      // TODO Auto-generated method stub
      examRepository.deleteById(id);
      
    }
  
  

}