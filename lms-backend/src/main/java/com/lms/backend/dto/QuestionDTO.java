package com.lms.backend.dto;

import java.util.List;
import lombok.Data;

@Data
public class QuestionDTO {
  private int id;
  private String questionText;
  private String correctAnswer;
  private List<String> options;

}