package com.lms.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Module {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    private Integer examId;
	    private Integer assignmentId;

	    private String name;
	    private String description;
	    
	    @ManyToOne
	    @JoinColumn(name = "course_id",nullable = false)
	    private Course course;
	    
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getExamId() {
			return examId;
		}
		public void setExamId(Integer examId) {
			this.examId = examId;
		}
		public Integer getAssignmentId() {
			return assignmentId;
		}
		public void setAssignmentId(Integer assignmentId) {
			this.assignmentId = assignmentId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Course getCourse() {
			return course;
		}
		public void setCourse(Course course) {
			this.course = course;
		}
		
	    
	}

	

