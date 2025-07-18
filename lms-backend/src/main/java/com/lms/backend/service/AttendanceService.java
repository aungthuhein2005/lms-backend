package com.lms.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.backend.dto.AttendanceRequest;
import com.lms.backend.entity.Attendance;
import com.lms.backend.entity.Type;
import com.lms.backend.repository.AttendanceRepository;
import com.lms.backend.repository.StudentRepository;
import com.lms.backend.repository.TeacherRepository;

@Service
public class AttendanceService {
  
  @Autowired TeacherRepository teacherRepository;
@Autowired AttendanceRepository attendanceRepository;
@Autowired StudentRepository studentRepository;
  
  public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }
  
  public List<Attendance> getAttendancesByTypes(Type type) {
      return attendanceRepository.findByType(type);
  } 
  
  public Optional<Attendance> getAttendanceById(Integer id){
    return attendanceRepository.findById(id);
  }
  
  public Attendance createAttendance(AttendanceRequest attendanceRequest) {
    Attendance createAttendance = new Attendance();
    if(attendanceRequest.getStudentId()!=null)createAttendance.setStudent(studentRepository.findById(attendanceRequest.getStudentId()).orElse(null));
    if(attendanceRequest.getTeacherId()!=null)createAttendance.setTeacher(teacherRepository.findById(attendanceRequest.getTeacherId()).orElse(null));
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
      if(attendanceDetails.getStudentId()!=null)existingAttendance.setStudent(studentRepository.findById(attendanceDetails.getStudentId()).orElse(null));
      if(attendanceDetails.getTeacherId()!=null)existingAttendance.setTeacher(teacherRepository.findById(attendanceDetails.getTeacherId()).orElse(null));
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