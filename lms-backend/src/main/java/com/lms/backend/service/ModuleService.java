package com.lms.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lms.backend.entity.Course;
import com.lms.backend.entity.CourseModule;
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
	
	public List<CourseModule> getAllModules() {
        return (List<CourseModule>) moduleRepository.findAll();
    }
	
	public CourseModule createModule(CourseModule module,Integer courseId) {
		Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
		module.setCourse(course);
		return moduleRepository.save(module);
    }
	
	public CourseModule updateModule(Integer id, CourseModule updateModule) {
	    Optional<CourseModule> optionalModule = moduleRepository.findById(id);
	    
	    if (optionalModule.isEmpty()) {
	        return null;
	    }

	    CourseModule existingModule = optionalModule.get();
	    existingModule.setName(updateModule.getName());
	    existingModule.setDescription(updateModule.getDescription());

	    return moduleRepository.save(existingModule);
	}
	
	public void deleteModule(Integer id) {
        moduleRepository.deleteById(id);
    }

	public List<CourseModule> findByCourseId(Integer courseId) {
		return moduleRepository.findByCourseId(courseId);
	}
	public List<CourseModule> getModulesByCourseId(Integer courseId) {
	    return moduleRepository.findByCourseId(courseId);
	}


}
