package com.lms.backend.entity;


import java.time.LocalDate;

import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
//import lombok.AllArgsConstructor;
//import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
    
    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Student student;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Teacher teacher;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Admin admin;

	
    
    
}