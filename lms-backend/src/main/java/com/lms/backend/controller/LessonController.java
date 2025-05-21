package com.lms.backend.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.lms.backend.entity.Lesson;
import com.lms.backend.service.LessonService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/lessons")
public class LessonController {
	
	  @Autowired
	    private LessonService lessonService;

	    @PostMapping("/create")
	    public Lesson createLesson(@RequestBody Lesson lesson) {
	        return lessonService.createLesson(lesson);
	    }

	    @GetMapping("/module/{moduleId}")
	    public List<Lesson> getLessonsByModuleId(@PathVariable Integer moduleId) {
	        return lessonService.getLessonsByModuleId(moduleId);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Lesson> getLesson(@PathVariable Integer id) {
	        return lessonService.getLessonById(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	    }

	    @PutMapping("/{id}")
	    public Lesson updateLesson(@PathVariable Integer id, @RequestBody Lesson lesson) {
	        return lessonService.updateLesson(id, lesson);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteLesson(@PathVariable Integer id) {
	        lessonService.deleteLesson(id);
	        return ResponseEntity.noContent().build();
	    }
}
