package com.lms.backend.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Exam {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String title;
  private String description;
  private Integer duration;
  private Integer score;

  @OneToMany(mappedBy = "exam",cascade = CascadeType.ALL)
  private List<Question> questions = new ArrayList<>();
  
  public void setQuestions(List<Question> questions) {
    this.questions.clear();
    if(questions != null) {
      for(Question q:questions) {
        q.setExam(this);
        this.questions.add(q);
      }
    }
  }
  
}