package com.lms.backend.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	@Column(name="created_at")
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name = "class_id")
	private ClassEntity classes;
	
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;



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