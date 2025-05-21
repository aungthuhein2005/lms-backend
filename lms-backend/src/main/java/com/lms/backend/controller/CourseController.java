package com.lms.backend.controller;

import java.util.List;
import java.util.Optional;

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
import com.lms.backend.entity.Course;
import com.lms.backend.entity.Module;
import com.lms.backend.repository.CourseRepository;
import com.lms.backend.repository.ModuleRepository;
import com.lms.backend.service.CourseService;
import com.lms.backend.service.ModuleService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/courses")


public class CourseController {

	private final CourseService courseService;
	private final CourseRepository courseRepository;
	private final ModuleService moduleService;
	private final ModuleRepository moduleRepository;

    public CourseController(CourseService courseService,CourseRepository courseRepository,ModuleService moduleService,ModuleRepository
    		moduleRepository) {
        this.courseService = courseService;
		this.courseRepository = courseRepository;
		this.moduleService = moduleService;
		this.moduleRepository = moduleRepository;
    }

	
	@GetMapping("/all")
	public List<Course> getAllCourse(){
		return courseService.getAllCourses();
	}
	
	@GetMapping("/title/{title}")
	public ResponseEntity<Course> getCourseByName(@PathVariable String title){
		Optional<Course> course = courseService.getCourseByTitle(title);
		return course.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
		
	}
	
	@GetMapping("/view/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable Integer id){
		Optional<Course> course = courseService.getCourseById(id);
		return course.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
		
	}
	
	@GetMapping("/{courseId}/modules")
	List<Module> getAllModule(@PathVariable Integer courseId){
		return moduleService.findByCourseId(courseId);
	}
	
	@PostMapping("/create")
	public Course createCourse(@RequestBody Course course) {
		return courseService.createCourse(course);
	}
	
	@PutMapping("/update/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public Course updateCourse(@PathVariable Integer id, @RequestBody Course course) {
	    return courseService.updateCourse(id, course);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Integer id){
		courseService.deleteCourse(id);
		return ResponseEntity.noContent().build();
	}
}
