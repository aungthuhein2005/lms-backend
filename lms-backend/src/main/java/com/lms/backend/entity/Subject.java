package com.lms.backend.entity;

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
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // e.g., "Mathematics"
    private String code; // optional e.g., "MATH101"

    private String description;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Course> courses;
}
