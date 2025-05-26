package com.lms.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.lms.backend.entity.ClassEntity;
import com.lms.backend.repository.ClassRepository;

@Service
public class ClassService {
  
  private final ClassRepository classRepository;

  public ClassService(ClassRepository classRepository) {
    super();
    this.classRepository = classRepository;
  }
  
  public List<ClassEntity> getAllClasses(){
    return (List<ClassEntity>) classRepository.findAll();
  }
  
  public Optional<ClassEntity> getClassById(Integer id){
    return classRepository.findById(id);
  }
  
  public Optional<ClassEntity> getClassByName(String name){
    return classRepository.findByName(name);
  }
  
  public ClassEntity createClass(ClassEntity classData) {
    return classRepository.save(classData);
  }
  
  public ClassEntity updateClass(Integer id,ClassEntity updateClass) {
      Optional<ClassEntity> optionalClass =classRepository.findById(id);
      
      if(optionalClass.isEmpty()) {
        return null;
      }
      
      ClassEntity existingClass = optionalClass.get();
      existingClass.setName(updateClass.getName());
      existingClass.setDescription(updateClass.getDescription());

      return classRepository.save(existingClass);
    }
  
  public void deleteClass(Integer id) {
    classRepository.deleteById(id);
  }

}