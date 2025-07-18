package com.lms.backend.repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

import com.lms.backend.dto.SemesterClassCountDTO;
import com.lms.backend.entity.ClassEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClassRepository extends CrudRepository<ClassEntity, Integer> {
  
  Optional<ClassEntity> findByName(String name);
  List<ClassEntity> findAllByTeacherId(int id);
  List<ClassEntity> findByTeacherId(int teacherId);
  List<ClassEntity> findAllByOrderByIdDesc(Pageable pageable);
  List<ClassEntity> findBySemesterId(int semesterId);
  @Query("SELECT new com.lms.backend.dto.SemesterClassCountDTO(s.id, s.name, COUNT(c)) " +
	       "FROM ClassEntity c JOIN c.semester s GROUP BY s.id, s.name")
	List<SemesterClassCountDTO> countClassesBySemester();

}