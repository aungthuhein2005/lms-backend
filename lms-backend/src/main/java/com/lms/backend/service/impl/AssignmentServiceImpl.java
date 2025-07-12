package com.lms.backend.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.lms.backend.dto.AssignmentCreateDTO;
import com.lms.backend.dto.AssignmentSubmissionDTO;
import com.lms.backend.entity.Assignment;
import com.lms.backend.entity.AssignmentSubmission;
import com.lms.backend.entity.ClassEntity;
import com.lms.backend.entity.Course;
import com.lms.backend.entity.Enrollment;
import com.lms.backend.entity.Lesson;
import com.lms.backend.entity.Module;
import com.lms.backend.entity.Student;
import com.lms.backend.entity.Subject;
import com.lms.backend.repository.AssignmentRepository;
import com.lms.backend.repository.AssignmentSubmissionRepository;
import com.lms.backend.repository.ClassRepository;
import com.lms.backend.repository.LessonRepository;
import com.lms.backend.repository.ModuleRepository;
import com.lms.backend.repository.StudentRepository;
import com.lms.backend.repository.TeacherRepository;
import com.lms.backend.service.AssignmentService;

@Service
public class AssignmentServiceImpl implements AssignmentService {
	  @Autowired AssignmentRepository assignmentRepo;
	    @Autowired AssignmentSubmissionRepository submissionRepo;
	    @Autowired LessonRepository lessonRepo;
	    @Autowired StudentRepository studentRepo;
	    @Autowired ClassRepository classRepository;
	    @Autowired ModuleRepository moduleRepository;
	    @Autowired LessonRepository lessonRepository;	
	    @Autowired TeacherRepository teacherRepository;
	    
	    @Autowired EnrollmentServiceImpl enrollmentServiceImpl;

	    public Assignment createAssignment(AssignmentCreateDTO dto) {
	        Assignment a = new Assignment();
	        a.setTitle(dto.getTitle());
	        a.setDescription(dto.getDescription());
	        a.setDueDate(dto.getDueDate());
	        a.setMedia(dto.getMedia());
	        a.setPoint(dto.getPoint());
	        a.setClassEntity(classRepository.findById(dto.getClassId())
	        	    .orElseThrow(() -> new RuntimeException("Class not found with ID: " + dto.getClassId())));
	        a.setTeacher(teacherRepository.findById(dto.getTeacherId())
	        		.orElse(null));
	        return assignmentRepo.save(a);
	    }
	    
		@Override
		public List<Assignment> getAssignmentsByclass(int classId) {
			return assignmentRepo.findAll().stream()
		            .filter(a -> a.getClassEntity().getId() == classId)
		            .collect(Collectors.toList());
		}

	    public List<AssignmentSubmission> getSubmissionsByAssignment(int assignmentId) {
	        return submissionRepo.findByAssignmentId(assignmentId);
	    }

	    public AssignmentSubmission submitAssignment(int assignmentId, int studentId, String fileUrl) {
	        AssignmentSubmission s = new AssignmentSubmission();
	        s.setAssignment(assignmentRepo.findById(assignmentId).orElseThrow());
	        s.setStudent(studentRepo.findById(studentId).orElseThrow());
	        s.setSubmittedFile(fileUrl);
	        s.setSubmittedAt(LocalDateTime.now());
	        return submissionRepo.save(s);
	    }
	    
	    public List<AssignmentSubmission> getSubmissionsByAssignmentAndTeacher(int assignmentId, int teacherId) {
	        Assignment assignment = assignmentRepo.findById(assignmentId)
	            .orElseThrow(() -> new RuntimeException("Assignment not found"));

	        // check if this assignment's class is taught by the teacher
	        if (assignment.getClassEntity().getTeacher().getId() != teacherId) {
	            throw new AccessDeniedException("You are not allowed to view submissions for this assignment");
	        }

	        return submissionRepo.findByAssignmentId(assignmentId);
	    }

//	    public List<Assignment> getAssignmentsByStudentId(int studentId) {
//	        Student student = studentRepo.findById(studentId)
//	                .orElseThrow(() -> new RuntimeException("Student not found"));
//
//	        List<Integer> classIds = student.getStudentClasses().stream()
//	                .map(enroll -> enroll.getClassEntity().getId())
//	                .toList();
//
//	        List<Course> courses = StreamSupport.stream(classRepository.findAllById(classIds).spliterator(), false)
//	                .map(ClassEntity::getCourse)
//	                .toList();
//
//	        List<Integer> courseIds = courses.stream().map(Course::getId).toList();
//	        List<Module> modules = moduleRepository.findByCourse_IdIn(courseIds);
//
//	        List<Integer> moduleIds = modules.stream().map(Module::getId).toList();
//	        if (moduleIds.isEmpty()) return Collections.emptyList();
//
//	        List<Lesson> lessons = lessonRepository.findByModule_IdIn(moduleIds);
//	        List<Integer> lessonIds = lessons.stream().map(Lesson::getId).toList();
//	        if (lessonIds.isEmpty()) return Collections.emptyList();
//
//	        return assignmentRepo.findByLessonIdIn(lessonIds);
//	    }



		@Override
		public List<Assignment> getAssignmentsByteacher(int teacherId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Assignment> getAssignmentsByStudentId(int studentId) {
			// TODO Auto-generated method stub
		     Student student = studentRepo.findById(studentId)
	                .orElseThrow(() -> new RuntimeException("Student not found"));
		     List<Integer> classIds = student.getStudentClasses().stream()
		                .map(enroll -> enroll.getClassEntity().getId())
		                .toList();
		     List<Assignment> assignments = assignmentRepo.findByClassEntity_IdIn(classIds);
			return assignments;
		}





}
