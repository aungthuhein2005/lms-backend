package com.lms.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.backend.dto.AttendanceRequest;
import com.lms.backend.entity.Attendance;
import com.lms.backend.repository.AttendanceRepository;
import com.lms.backend.repository.TeacherRepository;

@Service
public class AttendanceService {
  
  private  final AttendanceRepository attendanceRepository;
  
  @Autowired TeacherRepository teacherRepository;

  @Autowired
  public AttendanceService(AttendanceRepository attendanceRepository) {
    super();
    this.attendanceRepository = attendanceRepository;
  }
  
  public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }
  
  public Optional<Attendance> getAttendanceById(Integer id){
    return attendanceRepository.findById(id);
  }
  
  public Attendance createAttendance(AttendanceRequest attendanceRequest) {
    Attendance createAttendance = new Attendance();
    createAttendance.setStudentId(attendanceRequest.getStudentId());
    createAttendance.setTeacher(teacherRepository.findById(attendanceRequest.getTeacherId()).orElse(null));
    createAttendance.setClassId(attendanceRequest.getClassId());
    createAttendance.setType(attendanceRequest.getType());
    createAttendance.setStatus(attendanceRequest.getStatus());
    createAttendance.setDate(attendanceRequest.getDate());
    createAttendance.setRemark(attendanceRequest.getRemark());
    return attendanceRepository.save(createAttendance);
  }
  
  public Attendance updateAttendance(Integer id,AttendanceRequest attendanceDetails) {
    Optional<Attendance> attendance = attendanceRepository.findById(id);
    if(attendance.isPresent()) {
      Attendance existingAttendance = attendance.get();
      existingAttendance.setStudentId(attendanceDetails.getStudentId());
      existingAttendance.setTeacher(teacherRepository.findById(attendanceDetails.getTeacherId()).orElse(null));
            existingAttendance.setClassId(attendanceDetails.getClassId());
            existingAttendance.setType(attendanceDetails.getType());
            existingAttendance.setStatus(attendanceDetails.getStatus());
            existingAttendance.setDate(attendanceDetails.getDate());
            existingAttendance.setRemark(attendanceDetails.getRemark());
            return attendanceRepository.save(existingAttendance);
    }else {
      return null;
    }
  }
  
  public void deleteAttdance(Integer id) {
    attendanceRepository.deleteById(id);
  }
  
}