package com.lms.backend.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.lms.backend.dto.AssignmentCreateDTO;
import com.lms.backend.dto.AssignmentSubmissionDTO;
import com.lms.backend.dto.AssignmentWithStatusDTO;
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
	    	System.out.println(assignmentId);
	        return submissionRepo.findByAssignment_Id(assignmentId);
	    }

	    public AssignmentSubmission submitAssignment(int assignmentId, int studentId, String fileUrl) {
	        AssignmentSubmission s = new AssignmentSubmission();
	        s.setAssignment(assignmentRepo.findById(assignmentId).orElseThrow(null));
	        s.setStudent(studentRepo.findById(studentId).orElseThrow(null));
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

	        return submissionRepo.findByAssignment_Id(assignmentId);
	    }


		@Override
		public List<Assignment> getAssignmentsByteacher(int teacherId) {
			// TODO Auto-generated method stub
			List<Assignment> assignementList = assignmentRepo.findByTeacherId(teacherId);
			return assignementList;
		}
		
		@Override
		public List<AssignmentWithStatusDTO> getAssignmentsByStudentId(int studentId) {
		    Student student = studentRepo.findById(studentId)
		        .orElseThrow(() -> new RuntimeException("Student not found"));

		    List<Integer> classIds = student.getStudentClasses().stream()
		        .map(enroll -> enroll.getClassEntity().getId())
		        .toList();

		    if (classIds.isEmpty()) {
		        return Collections.emptyList(); // No classes, so no assignments
		    }

		    List<Assignment> assignments = assignmentRepo.findByClassEntity_IdIn(classIds);

		    // Get all submissions by this student
		    List<AssignmentSubmission> submissions = submissionRepo.findByStudent_id(studentId);

		    // Extract submitted assignment IDs
		    Set<Integer> submittedAssignmentIds = submissions.stream()
		        .map(sub -> sub.getAssignment().getId())
		        .collect(Collectors.toSet());

		    // Map assignments to DTO with status
		    List<AssignmentWithStatusDTO> result = assignments.stream()
		        .map(assignment -> {
		            AssignmentWithStatusDTO dto = new AssignmentWithStatusDTO();
		            dto.setId(assignment.getId());
		            dto.setTitle(assignment.getTitle());
		            dto.setDescription(assignment.getDescription());
		            dto.setDueDate(assignment.getDueDate());
		            dto.setPoint(assignment.getPoint());
		            dto.setMedia(assignment.getMedia());
		            dto.setClassname(assignment.getClassEntity().getName());
		            dto.setTeacher(assignment.getTeacher().getUser().getName());
		            dto.setStatus(submittedAssignmentIds.contains(assignment.getId()) ? "Submitted" : "Pending");
		            return dto;
		        })
		        .collect(Collectors.toList());

		    return result;
		}


		@Override
		public AssignmentSubmission updateScore(Integer submissionId, Integer score) {
			Optional<AssignmentSubmission> optional = submissionRepo.findById(submissionId);
	        if (optional.isEmpty()) {
	            throw new IllegalArgumentException("Submission not found with ID: " + submissionId);
	        }

	        AssignmentSubmission submission = optional.get();
	        submission.setScore(score);
	        return submissionRepo.save(submission);
		}






}
