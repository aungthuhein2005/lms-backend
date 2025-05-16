package com.lms.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lms.backend.entity.Course;
import com.lms.backend.repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    public Course createCourse(Course course) {
        return courseRepository.save(course);
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
    
}
