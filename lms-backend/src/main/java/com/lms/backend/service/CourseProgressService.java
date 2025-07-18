package com.lms.backend.service;

import java.util.List;

import com.lms.backend.dto.StudentCoursePorgressDTO;
import com.lms.backend.dto.TeacherCourseProgressDTO;
import com.lms.backend.entity.StudentCourseStatus;

public interface CourseProgressService {
    void markLessonCompleted(Long studentId, Long lessonId);
    StudentCourseStatus getCourseStatus(Long studentId, int courseId);
    double calculateProgressPercent(Long studentId, int courseId);
    List<StudentCoursePorgressDTO> getStudentDashboardCourseProgress(int studentId);
    List<TeacherCourseProgressDTO> getTeacherDashboardCourseProgress(int teacherId);
}
