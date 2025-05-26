package com.lms.backend.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.lms.backend.dto.ClassDTO;
import com.lms.backend.entity.ClassEntity;
import com.lms.backend.service.ClassService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")// allow react fornted
@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
public class ClassController {
	
  private final ClassService classService;
  
  @GetMapping("/all")
    public ResponseEntity<List<ClassDTO>> getAllClass(){
	  List<ClassEntity> classes = classService.getAllClasses();
	  List<ClassDTO> result = classes.stream().map(ClassDTO::new).collect(Collectors.toList());
      return ResponseEntity.ok(result);
    }
  
  @GetMapping("/viewClass/{name}")
    public ResponseEntity<ClassEntity> getClassByName(@PathVariable String name){
      Optional<ClassEntity> classData = classService.getClassByName(name);
      return classData.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/view/{id}")
    public  ResponseEntity<ClassEntity> getClassById(@PathVariable Integer id){
      Optional<ClassEntity> classData = classService.getClassById(id);
      return classData.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/create")
    public ClassEntity createClass(@RequestBody ClassEntity classData) {
      return classService.createClass(classData);
    }
    
    @PutMapping("/update/{id}")
    public ClassEntity updateClass(@PathVariable Integer id, @RequestBody ClassEntity classData) {
      return classService.updateClass(id, classData);
      
  }    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Integer id){
      classService.deleteClass(id);
      return ResponseEntity.noContent().build();
    }
    

}