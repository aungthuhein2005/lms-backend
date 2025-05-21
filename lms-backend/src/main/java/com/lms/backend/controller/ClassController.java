package com.lms.backend.controller;

import java.util.List;
import java.util.Optional;
import com.lms.backend.entity.Class;
import com.lms.backend.service.ClassService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")// allow react fornted
@RestController
@RequestMapping("/class")
@RequiredArgsConstructor
public class ClassController {
	
  private final ClassService classService;
  
  @GetMapping("/all")
    public List<Class> getAllClass(){
      return classService.getAllClasses();
    }
  
  @GetMapping("/viewClass/{name}")
    public ResponseEntity<Class> getClassByName(@PathVariable String name){
      Optional<Class> classData = classService.getClassByName(name);
      return classData.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/view/{id}")
    public  ResponseEntity<Class> getClassById(@PathVariable Integer id){
      Optional<Class> classData = classService.getClassById(id);
      return classData.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/create")
    public Class createClass(@RequestBody Class classData) {
      return classService.createClass(classData);
    }
    
    @PostMapping("/update/{id}")
    public Class updateClass(@RequestBody Class classData) {
      return classService.updateClass(classData);
    }
  
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Integer id){
      classService.deleteClass(id);
      return ResponseEntity.noContent().build();
    }
    

}