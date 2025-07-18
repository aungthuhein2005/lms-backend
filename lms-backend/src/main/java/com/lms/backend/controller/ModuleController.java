package com.lms.backend.controller;

import java.util.List;

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
import com.lms.backend.entity.Module;
import com.lms.backend.repository.ModuleRepository;
import com.lms.backend.service.ModuleService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/modules")
public class ModuleController {
	private final ModuleService moduleService;
	private final ModuleRepository moduleRepository;
	
	public ModuleController(ModuleService moduleService,ModuleRepository moduleRepository) {
		this.moduleService = moduleService;
		this.moduleRepository = moduleRepository;
		
	}
	
	@PostMapping("/courses/{courseid}")
	public Module createModule(@RequestBody Module module, 
	                                 @PathVariable("courseid") Integer courseId) {
	    return moduleService.createModule(module, courseId);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteModule(@PathVariable Integer id){
		moduleService.deleteModule(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/update/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public Module updateModule(@PathVariable Integer id, @RequestBody Module module) {
	    return moduleService.updateModule(id, module);
	}

	@GetMapping("/courses/{courseid}")
	public List<Module> getModulesByCourseId(@PathVariable("courseid") Integer courseId) {
	    return moduleService.getModulesByCourseId(courseId);
	}
}
