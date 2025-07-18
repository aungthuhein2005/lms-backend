package com.lms.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.backend.dto.AnswerDTO;
import com.lms.backend.dto.ExamDTO;
import com.lms.backend.dto.ExamResultDTO;
import com.lms.backend.dto.GradeUpdateDTO;
import com.lms.backend.dto.StudentExamResultDTO;
import com.lms.backend.dto.StudentScoreDTO;
import com.lms.backend.entity.StudentExamResult;
import com.lms.backend.repository.StudentExamResultRepository;
import com.lms.backend.service.ExamService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/exams")
public class ExamController {

	private ExamService examService;
	@Autowired StudentExamResultRepository examResultRepository;
	 public ExamController(ExamService examService) {
	        this.examService = examService;
	    }
	
	@GetMapping("/all")
	public List<ExamDTO> getAll(){
		return examService.getAllExams();
	}
	
	@GetMapping("/{id}")
	public ExamDTO getexamById(@PathVariable Integer id) {
		return examService.getExamById(id);
	}
	
	@PostMapping("/create")
	public ExamDTO createExam(@RequestBody ExamDTO examDTO) {
		return examService.createExam(examDTO);
	}
	
	@PutMapping("/update/{id}")
	public ExamDTO updateExam(@PathVariable Integer id, @RequestBody ExamDTO examDTO) {
		return examService.updateExam(id, examDTO);		
	}
	
	@PutMapping("/update_grade")
	public ResponseEntity<?> updateExamGrade(@RequestBody GradeUpdateDTO update) {
		examService.updateExamGrade(update.getExamId(), update.getStudentId(),update.getGrade());		
		return ResponseEntity.ok("Success");
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteExam(@PathVariable Integer id) {
		examService.deleteExam(id);
	}
	
	@PostMapping("/{id}/submit/{studentId}")
	public ExamResultDTO submitExam(@PathVariable Integer id,@PathVariable Integer studentId, @RequestBody List<AnswerDTO> answers) {
	    return examService.submitExamAnswers(id, studentId, answers);
	}

	@GetMapping("/{id}/result")
	public ExamResultDTO getExamResult(@PathVariable Integer id) {
		return examService.getExamResultByExamId(id);
	}
	
	@GetMapping("/students/{studentId}")
	public List<StudentExamResultDTO> getStudentGrade(@PathVariable int studentId){
		return examResultRepository.getResultsByStudentId(studentId);
	}

	@GetMapping("/{id}/details")
	public List<StudentScoreDTO> getExamDetail(@PathVariable Integer id){
		return examService.getAttemptExamsById(id);
	}
}