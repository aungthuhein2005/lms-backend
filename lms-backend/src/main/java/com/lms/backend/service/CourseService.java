package com.lms.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.backend.dto.CourseDTO;
import com.lms.backend.entity.ClassEntity;
import com.lms.backend.entity.Course;
import com.lms.backend.repository.ClassRepository;
import com.lms.backend.repository.CourseRepository;
import com.lms.backend.repository.SubjectRepository;

@Service
public class CourseService {

	@Autowired
	private ClassRepository classRepository;
	
	@Autowired SubjectRepository subjectRepository;
    private final CourseRepository courseRepository;
    
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    public Course createCourse(CourseDTO course) {
    	Course newcourse = new Course();
    	newcourse.setTitle(course.getTitle());
    	newcourse.setDescription(course.getDescription());
    	newcourse.setSubject(subjectRepository.findById(course.getSubjectId()).orElse(null));
        return courseRepository.save(newcourse);
    }

    public List<Course> getAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Integer id) {
        return courseRepository.findById(id);
    }

    public Optional<Course> getCourseByTitle(String title) {
        return courseRepository.findByTitle(title); // Make sure this method exists in your repository
    }

    public Course updateCourse(Integer id,Course updateCourse) {
        Optional<Course> optionalCourse =courseRepository.findById(id);
        
        if(optionalCourse.isEmpty()) {
          return null;
        }
        Course existingCourse = optionalCourse.get();
        existingCourse.setTitle(updateCourse.getTitle());
        existingCourse.setDescription(updateCourse.getDescription());

        return courseRepository.save(existingCourse);
      }
    
    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }
    
    public List<Course> getCourseByTeacherId(int id) {
        List<ClassEntity> classes = classRepository.findByTeacherId(id);
        
        if (classes.isEmpty()) return new ArrayList<>();

        Set<Integer> courseIds = classes.stream()
                .map(cls -> cls.getCourse().getId())
                .collect(Collectors.toSet());

        return courseRepository.findAllById(courseIds); // this works if extending JpaRepository
    }

    
}
