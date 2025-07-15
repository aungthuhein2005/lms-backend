package com.lms.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lms.backend.dto.GradeDTO;
import com.lms.backend.entity.Exam;
import com.lms.backend.entity.Grade;
import com.lms.backend.entity.Student;
import com.lms.backend.repository.ExamRepository;
import com.lms.backend.repository.GradeRepository;
import com.lms.backend.repository.StudentRepository;
import com.lms.backend.service.GradeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;

    private GradeDTO mapToDTO(Grade grade) {
        GradeDTO dto = new GradeDTO();
        dto.setId(grade.getId());
        dto.setExamId(grade.getExam().getId());
        dto.setStudentId(grade.getStudent().getId());
        dto.setGrade(grade.getGrade());
        dto.setGradeType(grade.getGradeType() != null ? grade.getGradeType().name() : null);
        dto.setCreatedAt(grade.getCreatedAt());
        return dto;
    }

    private Grade mapToEntity(GradeDTO dto) {
        Grade grade = new Grade();
        grade.setGrade(dto.getGrade());
        grade.setGradeType(dto.getGradeType() != null ? Enum.valueOf(com.lms.backend.entity.GradeType.class, dto.getGradeType()) : null);
        grade.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : java.time.LocalDateTime.now());

        Student student = studentRepository.findById(dto.getStudentId())
            .orElseThrow(() -> new RuntimeException("Student not found with id " + dto.getStudentId()));
        grade.setStudent(student);

        Exam exam = examRepository.findById(dto.getExamId())
            .orElseThrow(() -> new RuntimeException("Exam not found with id " + dto.getExamId()));
        grade.setExam(exam);

        if(dto.getId() != 0) {
            grade.setId(dto.getId());
        }

        return grade;
    }

    @Override
    public GradeDTO createGrade(GradeDTO gradeDTO) {
        Grade grade = mapToEntity(gradeDTO);
        Grade saved = gradeRepository.save(grade);
        return mapToDTO(saved);
    }

    @Override
    public GradeDTO getGradeById(int id) {
        Grade grade = gradeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Grade not found with id " + id));
        return mapToDTO(grade);
    }

    @Override
    public List<GradeDTO> getAllGrades() {
        return gradeRepository.findAll().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public GradeDTO updateGrade(int id, GradeDTO gradeDTO) {
        Grade existing = gradeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Grade not found with id " + id));
        
        // Update fields
        existing.setGrade(gradeDTO.getGrade());
        existing.setGradeType(gradeDTO.getGradeType() != null ? Enum.valueOf(com.lms.backend.entity.GradeType.class, gradeDTO.getGradeType()) : null);
        
        Student student = studentRepository.findById(gradeDTO.getStudentId())
            .orElseThrow(() -> new RuntimeException("Student not found with id " + gradeDTO.getStudentId()));
        existing.setStudent(student);
        
        Exam exam = examRepository.findById(gradeDTO.getExamId())
            .orElseThrow(() -> new RuntimeException("Exam not found with id " + gradeDTO.getExamId()));
        existing.setExam(exam);

        Grade saved = gradeRepository.save(existing);
        return mapToDTO(saved);
    }

    @Override
    public void deleteGrade(int id) {
        gradeRepository.deleteById(id);
    }
}
