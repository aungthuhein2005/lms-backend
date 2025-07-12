package com.lms.backend.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Question {
  @Id
  @GeneratedValue(strategy =  GenerationType.IDENTITY)
  private int id;
  private String questionText;
  private String correctAnswer;
  
  @ElementCollection
  private List<String> options = new ArrayList<>();
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "exam_id")
  private Exam exam;
  

}