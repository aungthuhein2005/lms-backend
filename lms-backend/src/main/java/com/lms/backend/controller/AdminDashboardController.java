package com.lms.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.backend.dto.AcademicYearStudentCountDTO;
import com.lms.backend.dto.ClassStudentCountDTO;
import com.lms.backend.dto.ClassSummaryDTO;
import com.lms.backend.entity.ClassEntity;
import com.lms.backend.entity.Semester;
import com.lms.backend.repository.AcademicYearRepository;
import com.lms.backend.repository.ClassRepository;
import com.lms.backend.repository.EnrollmentRepository;
import com.lms.backend.repository.SemesterRepository;
import com.lms.backend.service.ClassService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin-dashboard")
public class AdminDashboardController {

	@Autowired private AcademicYearRepository academicYearRepository;
	@Autowired private EnrollmentRepository enrollmentRepository;
	@Autowired private SemesterRepository semesterRepository;
	@Autowired private ClassRepository classRepository;
	
	@Autowired private ClassService classService;
	
	@GetMapping("/studnet-per-academiceyear")
	public List<AcademicYearStudentCountDTO> getStudentPerAcademicYear() {
        return enrollmentRepository.countStudentsByAcademicYear();
    }
	
	@GetMapping("/semesters/{semesterId}/class-progress")
	public List<ClassSummaryDTO> getClassProgressBySemester(@PathVariable int semesterId) {
	    List<ClassEntity> classes = classRepository.findBySemesterId(semesterId);
	    
	    List<ClassSummaryDTO> progressList = classes.stream()
	        .map(c -> {
	            double progress = classService.calculateClassProgress(c.getId());
	            return new ClassSummaryDTO(c.getId(), c.getName(), progress);
	        })
	        .toList();

	    return progressList;
	}
	
	@GetMapping("semesters/{semesterId}/class-student-count")
	public List<ClassStudentCountDTO> getStudentCountPerClass(@PathVariable int semesterId) {
	    List<ClassEntity> classes = classRepository.findBySemesterId(semesterId);

	    return classes.stream()
	        .map(c -> new ClassStudentCountDTO(
	            c.getName(),
	            enrollmentRepository.countByClassEntity_Id(c.getId()) // Use count for better performance
	        ))
	        .toList();
	}


	
}
