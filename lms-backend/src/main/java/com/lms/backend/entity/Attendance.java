package com.lms.backend.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "attendance")
@Data
public class Attendance {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @Column(name = "student_id")
    private Integer studentId;

  @ManyToOne
  @JoinColumn(name = "teacher_id") // this maps the FK column
  private Teacher teacher;


    @Column(name = "class_id")
    private Integer classId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type; // Enum for student, teacher

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status; // Enum for attempt, absence

    @Column(name = "date")
    private LocalDate date;
    
    @Column(name = "remark")
    private String remark;
    
    public enum Status {
        attempt, absence,late
    }

  
        
}