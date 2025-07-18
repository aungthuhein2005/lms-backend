package com.lms.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lms.backend.entity.Attendance;
import com.lms.backend.entity.Type;

@Repository
public interface  AttendanceRepository extends JpaRepository<Attendance, Integer> {
	List<Attendance> findByType(Type type);
 
  
}