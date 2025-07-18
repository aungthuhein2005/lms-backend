package com.lms.backend.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name ="classes")
@Data
public class ClassEntity {
  @Id	
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  @Column(columnDefinition = "text")
  private String description;
  
  private String schedule;
  
  @ManyToOne
  @JoinColumn(name = "teacher_id")
  private Teacher teacher;
  
  
  @ManyToOne
  @JoinColumn(name = "semester_id",nullable = false)
  private Semester semester;
  
  @ManyToOne
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;

  @OneToMany(mappedBy = "classEntity", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<ClassSchedule> schedules;

}