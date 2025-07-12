package com.lms.backend.dto;

import com.lms.backend.entity.Attendance.Status;

import java.time.LocalDate;

import com.lms.backend.entity.Type;

import lombok.Data;

@Data
public class AttendanceRequest {
  private Integer studentId;
  private Integer  teacherId;
   private Integer classId;
   private Type type;
   private Status status;
   private LocalDate date;
   private String remark;
   private Integer sessionId;
}