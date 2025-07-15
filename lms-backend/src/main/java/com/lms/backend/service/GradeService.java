package com.lms.backend.service;

import java.util.List;
import com.lms.backend.dto.GradeDTO;

public interface GradeService {
    GradeDTO createGrade(GradeDTO gradeDTO);
    GradeDTO getGradeById(int id);
    List<GradeDTO> getAllGrades();
    GradeDTO updateGrade(int id, GradeDTO gradeDTO);
    void deleteGrade(int id);
    
}
