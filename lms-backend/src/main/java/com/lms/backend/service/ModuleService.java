package com.lms.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lms.backend.entity.Course;
import com.lms.backend.entity.Module;
import com.lms.backend.repository.CourseRepository;
import com.lms.backend.repository.ModuleRepository;

@Service
public class ModuleService {
	
	private final ModuleRepository moduleRepository;
	private final CourseRepository courseRepository;
	
	public ModuleService(ModuleRepository moduleRepository, CourseRepository courseRepository) {
		this.moduleRepository = moduleRepository;
		this.courseRepository = courseRepository;
	}
	
	public List<Module> getAllModules() {
        return (List<Module>) moduleRepository.findAll();
    }
	
	public Module createModule(Module module,Integer courseId) {
		Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
		module.setCourse(course);
		return moduleRepository.save(module);
    }
	
	public Module updateModule(Integer id, Module updateModule) {
	    Optional<Module> optionalModule = moduleRepository.findById(id);
	    
	    if (optionalModule.isEmpty()) {
	        return null;
	    }

	    Module existingModule = optionalModule.get();
	    existingModule.setName(updateModule.getName());
	    existingModule.setDescription(updateModule.getDescription());

	    return moduleRepository.save(existingModule);
	}
	
	public void deleteModule(Integer id) {
        moduleRepository.deleteById(id);
    }

	public List<Module> findByCourseId(Integer courseId) {
		return moduleRepository.findByCourseId(courseId);
	}
	public List<Module> getModulesByCourseId(Integer courseId) {
	    return moduleRepository.findByCourseId(courseId);
	}


}
