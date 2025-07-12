package com.lms.backend.repository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import com.lms.backend.entity.ClassEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClassRepository extends CrudRepository<ClassEntity, Integer> {
  
  Optional<ClassEntity> findByName(String name);
  List<ClassEntity> findAllByTeacherId(int id);
  List<ClassEntity> findByTeacherId(int teacherId);
  List<ClassEntity> findAllByOrderByIdDesc(Pageable pageable);
}