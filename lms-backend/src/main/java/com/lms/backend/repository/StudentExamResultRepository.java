package com.lms.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lms.backend.dto.StudentScoreDTO;
import com.lms.backend.entity.StudentExamResult;

public interface StudentExamResultRepository extends CrudRepository<StudentExamResult,Integer >{

	@Query("SELECT new com.lms.backend.dto.StudentScoreDTO(s.student.id, s.score) " +
	           "FROM StudentExamResult s WHERE s.exam.id = :examId")
	List<StudentScoreDTO> findResultsByExamId(Integer examId);
}
