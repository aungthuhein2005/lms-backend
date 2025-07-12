package com.lms.backend.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.lms.backend.dto.ClassCreateDTO;
import com.lms.backend.dto.ClassDTO;
import com.lms.backend.dto.StudentDTO;
import com.lms.backend.entity.ClassEntity;
import com.lms.backend.entity.Student;
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
import org.springframework.web.bind.annotation.RequestParam;
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
  
  @GetMapping("/recent")
  public List<ClassEntity> getRecentClasses(@RequestParam(defaultValue = "5") int limit) {
      return classService.getRecentClasses(limit);
  }

  

    
    @GetMapping("/{id}")
    public  ResponseEntity<ClassEntity> getClassById(@PathVariable Integer id){
      Optional<ClassEntity> classData = classService.getClassById(id);
      return classData.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/create")
    public ClassEntity createClass(@RequestBody ClassCreateDTO classData) {
      return classService.createClass(classData);
    }
    
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<ClassEntity>> getClassesByTeacherId(@PathVariable int teacherId) {
        List<ClassEntity> classes = classService.getClassesByTeacherId(teacherId);
        if (classes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(classes);
    }
    
    @PutMapping("/update/{id}")
    public ClassEntity updateClass(@PathVariable Integer id, @RequestBody ClassEntity classData) {
      return classService.updateClass(id, classData);
      
  }    
    
    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentDTO>> getStudentsByClass(@PathVariable Integer id) {
        List<Student> students = classService.getStudentsByClassId(id);

        // Optional: Convert to DTO
        List<StudentDTO> studentDTOs = students.stream()
            .map(student -> new StudentDTO(student.getId(), student.getUser().getName(), student.getUser().getEmail(),student.getEnroll_date()))
            .toList();

        return ResponseEntity.ok(studentDTOs);
    }
    


    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Integer id){
      classService.deleteClass(id);
      return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/student/{studentId}")
    public List<ClassEntity> getClassesByStudentId(@PathVariable int studentId) {
        return classService.getClassesByStudentId(studentId);
    }
    

}