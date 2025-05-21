package com.lms.backend.repository;

import java.util.Optional;
import com.lms.backend.entity.Class;
import org.springframework.data.repository.CrudRepository;

public interface ClassRepository extends CrudRepository<Class, Integer> {
  
  Optional<Class> findByName(String name);

}