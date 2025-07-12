package com.lms.backend.service;

import java.util.List;

import com.lms.backend.dto.AssignmentCreateDTO;
import com.lms.backend.dto.AssignmentSubmissionDTO;
import com.lms.backend.entity.Assignment;
import com.lms.backend.entity.AssignmentSubmission;

public interface AssignmentService {
    Assignment createAssignment(AssignmentCreateDTO dto);
    List<Assignment> getAssignmentsByclass(int classId);
    List<Assignment> getAssignmentsByteacher(int teacherId);
    List<AssignmentSubmission> getSubmissionsByAssignment(int assignmentId);
    AssignmentSubmission submitAssignment(int assignmentId, int studentId, String fileUrl);
    List<AssignmentSubmission> getSubmissionsByAssignmentAndTeacher(int assignmentId,int teacherId);
    List<Assignment> getAssignmentsByStudentId(int studentId);

}