package com.lms.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private String mediaURL;
    private String mediaType;
    private String mediaFileName;
    
    @Column(nullable = true)
    private Integer examId;
    @Column(nullable = true)
    private Integer assignmentId;

    @ManyToOne
    @JoinColumn(name = "module_id",nullable = false)
    private Module module;

	

    
}
