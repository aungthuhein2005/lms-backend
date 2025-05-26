package com.lms.backend.repository;

import java.util.Optional;
import com.lms.backend.entity.ClassEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClassRepository extends CrudRepository<ClassEntity, Integer> {
  
  Optional<ClassEntity> findByName(String name);

}