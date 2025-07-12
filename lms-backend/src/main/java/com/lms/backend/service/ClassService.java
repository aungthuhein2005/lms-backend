package com.lms.backend.service;

import java.time.DayOfWeek; 
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.lms.backend.dto.ClassCreateDTO;
import com.lms.backend.entity.ClassEntity;
import com.lms.backend.entity.ClassSchedule;
import com.lms.backend.entity.Course;
import com.lms.backend.entity.Enrollment;
import com.lms.backend.entity.Semester;
import com.lms.backend.entity.Student;
import com.lms.backend.entity.Teacher;
import com.lms.backend.repository.ClassRepository;
import com.lms.backend.repository.CourseRepository;
import com.lms.backend.repository.EnrollmentRepository;
import com.lms.backend.repository.SemesterRepository;
import com.lms.backend.repository.TeacherRepository;

@Service
public class ClassService {
	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private SemesterRepository semesterRepository;

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;

  
  public List<ClassEntity> getAllClasses(){
    return (List<ClassEntity>) classRepository.findAll();
  }
  
  public Optional<ClassEntity> getClassById(Integer id){
    return classRepository.findById(id);
  }
  
  public Optional<ClassEntity> getClassByName(String name){
    return classRepository.findByName(name);
  }
  
  public ClassEntity createClass(ClassCreateDTO classData) {

	    Course course = courseRepository.findById(classData.getCourse_id()).orElse(null);
	    Semester semester = semesterRepository.findById(classData.getSemester_id()).orElse(null);
	    Teacher teacher = teacherRepository.findById(classData.getTeacher_id()).orElse(null);

	    ClassEntity classEntity = new ClassEntity();
	    classEntity.setName(classData.getName());
	    classEntity.setDescription(classData.getDescription());
	    classEntity.setCourse(course);
	    classEntity.setSemester(semester);
	    if (teacher != null) classEntity.setTeacher(teacher);

	    // Process schedule list
	    List<ClassSchedule> schedules = new ArrayList<>();
	    if (classData.getSchedules() != null) {
	        for (ClassCreateDTO.ScheduleDTO scheduleDTO : classData.getSchedules()) {
	            ClassSchedule schedule = new ClassSchedule();
	            schedule.setDayOfWeek(DayOfWeek.valueOf(scheduleDTO.getDayOfWeek())); // Ensure enum format
	            schedule.setStartTime(LocalTime.parse(scheduleDTO.getStartTime()));
	            schedule.setEndTime(LocalTime.parse(scheduleDTO.getEndTime()));
	            schedule.setClassEntity(classEntity); // important
	            schedules.add(schedule);
	        }
	    }

	    classEntity.setSchedules(schedules);

	    return classRepository.save(classEntity);
	}

  public ClassEntity updateClass(Integer id,ClassEntity updateClass) {
      Optional<ClassEntity> optionalClass =classRepository.findById(id);
      
      if(optionalClass.isEmpty()) {
        return null;
      }
      
      ClassEntity existingClass = optionalClass.get();
      existingClass.setName(updateClass.getName());
      existingClass.setDescription(updateClass.getDescription());

      return classRepository.save(existingClass);
    }
  
  public void deleteClass(Integer id) {
    classRepository.deleteById(id);
  }
  
  public Teacher getClassTeacher(int classId) {
	  ClassEntity classEntity = classRepository.findById(classId).orElse(null);
	  return classEntity.getTeacher();
  }
  
  public List<Student> getStudentsByClassId(int classId) {
	    List<Enrollment> enrollments = enrollmentRepository.findByClassEntityId(classId);
	    return enrollments.stream()
	                      .map(Enrollment::getStudent)
	                      .collect(Collectors.toList());
	}
  
  public List<ClassEntity> getClassesByTeacherId(int teacherId) {
	    return classRepository.findByTeacherId(teacherId);
	}

  public List<ClassEntity> getRecentClasses(int limit) {
	    return classRepository.findAllByOrderByIdDesc(PageRequest.of(0, limit));
	}


  public List<ClassEntity> getClassesByStudentId(int studentId) {
      List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
      return enrollments.stream()
              .map(Enrollment::getClassEntity)
              .collect(Collectors.toList());
  }

}