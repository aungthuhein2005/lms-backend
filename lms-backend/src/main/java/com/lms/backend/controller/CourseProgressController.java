package com.lms.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.backend.dto.LessonCompleteRequest;
import com.lms.backend.entity.StudentCourseStatus;
import com.lms.backend.service.CourseProgressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/progress")
@RequiredArgsConstructor
public class CourseProgressController {

    private final CourseProgressService progressService;

    @PostMapping("/complete")
    public ResponseEntity<?> completeLesson(@RequestBody LessonCompleteRequest request) {
        progressService.markLessonCompleted(request.getStudentId(), request.getLessonId());
        return ResponseEntity.ok("Lesson marked as completed.");
    }

    @GetMapping("/status")
    public ResponseEntity<StudentCourseStatus> getStatus(@RequestParam Long studentId, @RequestParam int courseId) {
        return ResponseEntity.ok(progressService.getCourseStatus(studentId, courseId));
    }
    
    @GetMapping("/progress-percent")
    public ResponseEntity<Double> getProgressPercent(
            @RequestParam Long studentId,
            @RequestParam int courseId) {

        double percent = progressService.calculateProgressPercent(studentId, courseId);
        return ResponseEntity.ok(percent);
    }
    
    @GetMapping("/student-dashboard-summary")
    public ResponseEntity<?> getDashboardProgress(@RequestParam int studentId) {
        return ResponseEntity.ok(progressService.getStudentDashboardCourseProgress(studentId));
    }


}
