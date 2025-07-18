package com.lms.backend.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.backend.dto.AnswerDTO;
import com.lms.backend.dto.ExamDTO;
import com.lms.backend.dto.ExamResultDTO;
import com.lms.backend.dto.QuestionDTO;
import com.lms.backend.dto.QuestionResultDTO;
import com.lms.backend.dto.RecentExamDTO;
import com.lms.backend.dto.StudentScoreDTO;
import com.lms.backend.dto.SubmittedAnswerDTO;
import com.lms.backend.entity.ClassEntity;
import com.lms.backend.entity.Exam;
import com.lms.backend.entity.Question;
import com.lms.backend.entity.Student;
import com.lms.backend.entity.StudentExamResult;
import com.lms.backend.repository.ClassRepository;
import com.lms.backend.repository.ExamRepository;
import com.lms.backend.repository.QuestionRepository;
import com.lms.backend.repository.StudentExamResultRepository;
import com.lms.backend.repository.StudentRepository;
import com.lms.backend.repository.TeacherRepository;
import com.lms.backend.service.ExamService;

@Service
public class ExamServiceImpl implements ExamService{

	private ExamRepository examRepository;
	 private final QuestionRepository questionRepository;
	 private ClassRepository classRepository;
	 private StudentRepository studentRepository;
	 private StudentExamResultRepository studentExamResultRepository;
	 private TeacherRepository teacherRepository;

	 public ExamServiceImpl(ExamRepository examRepository, QuestionRepository questionRepository,ClassRepository classRepository,StudentRepository studentRepository,StudentExamResultRepository studentExamResultRepository
) {
	        this.examRepository = examRepository;
	        this.questionRepository = questionRepository;
	        this.classRepository = classRepository;
	        this.studentRepository = studentRepository;
	        this.studentExamResultRepository = studentExamResultRepository;
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
				exam.setClassId(e.getClasses() != null ? e.getClasses().getId() : null);

				List<QuestionDTO> questions = new ArrayList<>();
				for(Question q:e.getQuestions()) {
					QuestionDTO question = new QuestionDTO();
					question.setId(q.getId());
					question.setQuestionText(q.getQuestionText());
					question.setCorrecrtOption(q.getCorrectOption());
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
			exam.setClassId(e.getClasses() != null ? e.getClasses().getId() : null);

			
			List<QuestionDTO> questions = new ArrayList<>();
			for(Question q: e.getQuestions()) {
				QuestionDTO question = new QuestionDTO();
				question.setId(q.getId());
				question.setQuestionText(q.getQuestionText());
				question.setCorrecrtOption(q.getCorrectOption());
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
			exam.setTeacher(teacherRepository.findById(examDTO.getTeacherId()).orElse(null));
			exam.setDescription(examDTO.getDescription());
			exam.setDuration(examDTO.getDuration());
			exam.setScore(examDTO.getScore());
			if (examDTO.getClassId() != null) {
			   ClassEntity classes = classRepository.findById(examDTO.getClassId())
			        .orElseThrow(() -> new NoSuchElementException("Class not found"));
			    exam.setClasses(classes);
			}
			List<Question> questions = new ArrayList<>();
			if(examDTO.getQuestions() != null) {
				for(QuestionDTO qDTO: examDTO.getQuestions()) {
					Question q = new Question();
					q.setQuestionText(qDTO.getQuestionText());
					q.setCorrectOption(q.getCorrectOption());
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
				qDto.setCorrecrtOption(q.getCorrectOption());
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
			if (examDTO.getClassId() != null) {
			    ClassEntity classes = classRepository.findById(examDTO.getClassId())
			        .orElseThrow(() -> new NoSuchElementException("Class not found"));
			    exam.setClasses(classes);
			}

			
			List<Question> updateQuestions = new ArrayList<>();
			if(examDTO.getQuestions() != null) {
				for(QuestionDTO qDTO : examDTO.getQuestions()) {
					Question q = new Question();
					q.setId(qDTO.getId());
					q.setQuestionText(qDTO.getQuestionText());
					q.setCorrectOption(q.getCorrectOption());
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
			if (updated.getClasses() != null) {
			    examdto.setClassId(updated.getClasses().getId());
			    examdto.setClassName(updated.getClasses().getName());
			}
			
			List<QuestionDTO> questionsDto = new ArrayList<>();
			for(Question q : updated.getQuestions()) {
				QuestionDTO qDto =  new QuestionDTO();
				qDto.setId(q.getId());
				qDto.setQuestionText(q.getQuestionText());
				qDto.setCorrecrtOption(q.getCorrectOption());
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

		private final Map<Integer, ExamResultDTO> examResultStorage = new HashMap<>();

		@Override
		public ExamResultDTO submitExamAnswers(Integer examId,Integer studentId, List<AnswerDTO> answers) {
		    Exam exam = examRepository.findById(examId)
		        .orElseThrow(() -> new RuntimeException("Exam not found"));

		    Student student = studentRepository.findById(studentId)
	                .orElseThrow(() -> new RuntimeException("Student not found"));
		    
		    int total = exam.getQuestions().size();
		    int correct = 0;
		    int score = 0;
		    List<Integer> correctQuestionIds = new ArrayList<>();
		    List<SubmittedAnswerDTO> submittedAnswers = new ArrayList<>();

		    for (AnswerDTO answer : answers) {
		        for (Question q : exam.getQuestions()) {
		            if (q.getId() == (answer.getQuestionId())) {
		                boolean isCorrect = q.getCorrectOption() == answer.getSelectedOptionIndex();

		                submittedAnswers.add(new SubmittedAnswerDTO(
		                        q.getId(),
		                        q.getQuestionText(),
		                        q.getOptions().get(q.getCorrectOption()),           
		                        q.getOptions().get(answer.getSelectedOptionIndex())  
		                    ));
		               


		                if (isCorrect) {
		                    correct++;
		                    correctQuestionIds.add(q.getId());
		                }

		                break;
		            }
		        }
		    }

		    score = correct; 
		    
		    StudentExamResult examResult = new StudentExamResult();
		    examResult.setStudent(student);
		    examResult.setExam(exam);
		    examResult.setScore(score);
		    studentExamResultRepository.save(examResult);

		    ExamResultDTO result = new ExamResultDTO(
		    		 student.getId(),
		    		    exam.getId(),
		    		    total,
		    		    correct,
		    		    score,
		    		    correctQuestionIds,
		    		    submittedAnswers
		    );

		    
		    examResultStorage.put(examId, result);

		    return result;
		}

		
		@Override
		public ExamResultDTO getExamResultByExamId(Integer examId) {
		    ExamResultDTO result = examResultStorage.get(examId);
		    if (result == null) {
		        throw new RuntimeException("No result found for exam ID " + examId);
		    }
		    return result;
		}


		public List<StudentScoreDTO> getAttemptExamsById(Integer id) {
		    return studentExamResultRepository.findExamResultByExamId(id).stream()
		        .map(result -> {
		            String studentName = "Unknown User";
		            if (result.getStudent() != null && result.getStudent().getUser() != null) {
		                studentName = result.getStudent().getUser().getName();
		            }
		            return new StudentScoreDTO(result.getStudent().getId(), studentName, result.getScore(),result.getGrade());
		        })
		        .collect(Collectors.toList());
		}

		@Override
		public void updateExamGrade(Integer examId,int studentId,String grade) {
			// TODO Auto-generated method stub
			System.out.println("Hello");
			System.out.println(examId);
			System.out.println(studentId);
			StudentExamResult result = studentExamResultRepository.findExamResultByExamIdAndStudentId(examId,studentId);
			result.setGrade(grade);
			studentExamResultRepository.save(result);
		}

		@Override
		public List<RecentExamDTO> getRecentExamByTeacherId(int teacherId) {
	        List<Exam> exams = examRepository.findByTeacherIdOrderByCreatedAtDesc(teacherId);

	        return exams.stream().map(exam -> {
	            RecentExamDTO dto = new RecentExamDTO();
	            dto.setExamId(exam.getId());
	            dto.setExamTitle(exam.getTitle());
	            dto.setExamDescription(exam.getDescription());

	            dto.setTeacherId(exam.getTeacher().getId());
	            dto.setTeacherName(exam.getTeacher().getUser().getName()); // assumes Teacher has User

	            return dto;
	        }).collect(Collectors.toList());
	    }
		
	



	
}