package com.lms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lms.backend.entity.Attendance;

@Repository
public interface  AttendanceRepository extends JpaRepository<Attendance, Integer> {

  
  
}