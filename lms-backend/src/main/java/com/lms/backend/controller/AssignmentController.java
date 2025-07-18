package com.lms.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.backend.dto.AssignmentCreateDTO;
import com.lms.backend.dto.AssignmentSubmissionDTO;
import com.lms.backend.dto.AssignmentWithStatusDTO;
import com.lms.backend.entity.Assignment;
import com.lms.backend.entity.AssignmentSubmission;
import com.lms.backend.service.AssignmentService;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    @Autowired AssignmentService assignmentService;

    @PostMapping("/create")
    public Assignment create(@RequestBody AssignmentCreateDTO dto) {
        return assignmentService.createAssignment(dto);
    }

    @GetMapping("/class/{classId}")
    public List<Assignment> getByClass(@PathVariable int classId) {
        return assignmentService.getAssignmentsByclass(classId);
    }
    
    @GetMapping("/teacher/{teacherId}")
    public List<Assignment> getByTeacher(@PathVariable int teacherId) {
        return assignmentService.getAssignmentsByteacher(teacherId);
    }

    @PostMapping("/submit")
    public AssignmentSubmission submit(@RequestBody Map<String, String> body) {
        int assignmentId = Integer.parseInt(body.get("assignmentId"));
        int studentId = Integer.parseInt(body.get("studentId"));
        String fileUrl = body.get("fileUrl");

        return assignmentService.submitAssignment(assignmentId, studentId, fileUrl);
    }


    @GetMapping("/{assignmentId}/submissions")
    public List<AssignmentSubmission> getSubmissions(@PathVariable int assignmentId) {
        return assignmentService.getSubmissionsByAssignment(assignmentId);
    }
    
    @GetMapping("/{assignmentId}/submissions/teacher/{teacherId}")
    public List<AssignmentSubmission> getSubmissionsByTeacher(
        @PathVariable int assignmentId,
        @PathVariable int teacherId
    ) {
        return assignmentService.getSubmissionsByAssignmentAndTeacher(assignmentId, teacherId);
    }
    
    @GetMapping("/student/{studentId}")
    public List<AssignmentWithStatusDTO> getAssignmentsByStudentId(@PathVariable int studentId) {
        return assignmentService.getAssignmentsByStudentId(studentId);
    }

    @PatchMapping("submissions/{submissionId}/score")
    public AssignmentSubmission updateScore(
            @PathVariable Integer submissionId,
            @RequestBody Map<String, Integer> body
    ) {
        Integer score = body.get("score");
        return assignmentService.updateScore(submissionId, score);
    }
    

}
