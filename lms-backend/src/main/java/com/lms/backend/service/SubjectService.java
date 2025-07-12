package com.lms.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lms.backend.entity.Subject;
import com.lms.backend.repository.SubjectRepository;

@Service
public class SubjectService {
  
  private final SubjectRepository subjectRepository;

  public SubjectService(SubjectRepository subjectRepository) {
    super();
    this.subjectRepository = subjectRepository;
  }
  
  public Subject createSubject(Subject subject) {
    return subjectRepository.save(subject);
  }
  
  public List<Subject> getAllSubjects(){
    return(List<Subject>) subjectRepository.findAll();
  }
  
  public Optional<Subject> getSubjectById(Integer id){
    return subjectRepository.findById(id);
  }
  
  public Optional<Subject> getSubjectByName(String name){
    return subjectRepository.findByName(name);
  }
  
  public Subject updateSubject(Integer id,Subject updateSubject) {
    Optional<Subject> optionalSubject = subjectRepository.findById(id);
    
    if(optionalSubject.isEmpty()) {
      return null;
    }
    Subject existingSubject = optionalSubject.get();
    existingSubject.setName(updateSubject.getName());
    existingSubject.setDescription(updateSubject.getDescription());
    
    return subjectRepository.save(existingSubject);
  }
  
  public void deleteSubject(Integer id) {
    subjectRepository.deleteById(id);
  }
}