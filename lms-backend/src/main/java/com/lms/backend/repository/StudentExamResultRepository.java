package com.lms.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lms.backend.dto.StudentExamResultDTO;
import com.lms.backend.dto.StudentScoreDTO;
import com.lms.backend.entity.StudentExamResult;

public interface StudentExamResultRepository extends CrudRepository<StudentExamResult,Integer >{

	@Query("SELECT new com.lms.backend.dto.StudentScoreDTO(s.student.id, s.score) " +
	           "FROM StudentExamResult s WHERE s.exam.id = :examId")
	List<StudentScoreDTO> findResultsByExamId(Integer examId);
	
	List<StudentExamResult> findExamResultByExamId(Integer id);
	List<StudentExamResult> findExamResultByStudentId(Integer id);
	
	@Query("SELECT s FROM StudentExamResult s WHERE s.exam.id = :examId AND s.student.id = :studentId")
	StudentExamResult findExamResultByExamIdAndStudentId(@Param("examId") Integer examId, @Param("studentId") Integer studentId);
	
    @Query("SELECT s FROM StudentExamResult s WHERE s.exam.classes.id = :classId")
    List<StudentExamResult> findResultsByClassId(@Param("classId") Integer classId);
    @Query("SELECT new com.lms.backend.dto.StudentExamResultDTO(e.title, s.grade) FROM StudentExamResult s JOIN s.exam e WHERE s.student.id = :studentId")
    List<StudentExamResultDTO> getResultsByStudentId(@Param("studentId") Integer studentId);


}