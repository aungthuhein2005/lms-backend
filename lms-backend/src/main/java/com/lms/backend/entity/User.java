package com.lms.backend.entity;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String phone;

    @Column(unique = true, nullable = false)
    private String email;

    private String profile;

    @Column(columnDefinition = "text")
    @JsonIgnore
    private String password;

    private String role;
    
    @Enumerated(EnumType.STRING) 
    private Gender gender;

    
    private LocalDate dob;
}

