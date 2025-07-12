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
        return assignmentService.getAssignmentsByclass(teacherId);
    }

    @PostMapping("/{assignmentId}/submit/{studentId}")
    public AssignmentSubmission submit(@PathVariable int assignmentId, @PathVariable int studentId, @RequestBody Map<String, String> body) {
        return assignmentService.submitAssignment(assignmentId, studentId, body.get("fileUrl"));
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
    public List<Assignment> getAssignmentsByStudentId(@PathVariable int studentId) {
        return assignmentService.getAssignmentsByStudentId(studentId);
    }

    
//    @PatchMapping("/submissions/{submissionId}/score")
//    public ResponseEntity<AssignmentSubmission> updateScore(
//            @PathVariable int submissionId,
//            @RequestBody Map<String, Integer> body
//    ) {
//        AssignmentSubmission s = submissionRepo.findById(submissionId).orElseThrow();
//        s.setScore(body.get("score"));
//        return ResponseEntity.ok(submissionRepo.save(s));
//    }

}
