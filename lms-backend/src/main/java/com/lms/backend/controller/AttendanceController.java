package com.lms.backend.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.backend.dto.AttendanceRequest;
import com.lms.backend.entity.Attendance;
import com.lms.backend.repository.AttendanceRepository;
import com.lms.backend.service.AttendanceService;

@RestController
@RequestMapping("/attendances")
@CrossOrigin(origins = "http://localhost:3000")
public class AttendanceController {

  private final AttendanceService attendanceService;
  private final AttendanceRepository attendanceRepository;
  
  @Autowired
  public AttendanceController(AttendanceService attendanceService,AttendanceRepository attendanceRepository) {
    super();
    this.attendanceService = attendanceService;
    this.attendanceRepository = attendanceRepository;
    
  }
  
  @GetMapping
  public ResponseEntity<List<Attendance>> getAllAttendances() {
      return new ResponseEntity<>(attendanceService.getAllAttendances(), HttpStatus.OK);
  }

  
   @GetMapping("/{id}")
      public ResponseEntity<Attendance> getAttendanceById(@PathVariable Integer id) {
          Optional<Attendance> attendance = attendanceService.getAttendanceById(id);
          return attendance.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                  .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
      }
   @PostMapping
   public ResponseEntity<Attendance> createAttendance(@RequestBody AttendanceRequest attendanceRequest ) {
       Attendance createdAttendance = attendanceService.createAttendance(attendanceRequest);
       return new ResponseEntity<>(createdAttendance, HttpStatus.CREATED);
   }

   
   @PutMapping("/{id}")
   public ResponseEntity<Attendance> updateAttendance(@PathVariable Integer id, @RequestBody Attendance attendance) {
       if (!attendanceRepository.existsById(id)) {
           return ResponseEntity.notFound().build();
       }

       attendance.setId(id); 
       Attendance updated = attendanceRepository.save(attendance);
       return ResponseEntity.ok(updated);
     
//     Attendance updateAttendance = attendanceService.createAttendance(attendance);
//     return ResponseEntity.ok(updateAttendance);
     
   }

   
   @DeleteMapping("/{id}")
      public ResponseEntity<HttpStatus> deleteAttendance(@PathVariable Integer id) {
          try {
              attendanceService.deleteAttdance(id);
              return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
          } catch (Exception e) {
              return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Or appropriate error
          }
   }
}