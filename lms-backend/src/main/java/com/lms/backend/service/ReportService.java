package com.lms.backend.service;

import com.lms.backend.dto.*;

import java.util.List;

public interface ReportService {
    List<StudentGradeReportDTO> getStudentGradeReport();
//    List<TeacherAttendanceReportDTO> getTeacherAttendanceReport(int teacherId);
    List<ClassSummaryDTO> getClassPerformanceSummary();
    List<CourseEnrollmentSummaryDTO> getCourseEnrollmentSummary();
}
