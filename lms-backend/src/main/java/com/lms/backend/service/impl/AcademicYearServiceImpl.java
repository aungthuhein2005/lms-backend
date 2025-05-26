package com.lms.backend.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.backend.dto.AcademicYearDetailResponse;
import com.lms.backend.dto.AcademicYearRequest;
import com.lms.backend.dto.AcademicYearResponse;
import com.lms.backend.entity.AcademicYear;
import com.lms.backend.repository.AcademicYearRepository;
import com.lms.backend.service.AcademicYearService;

@Service
public class AcademicYearServiceImpl implements AcademicYearService {

    @Autowired
    private AcademicYearRepository academicYearRepository;

    @Override
    public AcademicYear createAcademicYear(AcademicYearRequest request) {
        AcademicYear year = new AcademicYear();
        year.setName(request.getName());
        return academicYearRepository.save(year);
    }

    @Override
    public AcademicYear updateAcademicYear(int id,AcademicYearRequest request) {
        Optional<AcademicYear> optional = academicYearRepository.findById(id);
        if (optional.isPresent()) {
            AcademicYear year = optional.get();
            year.setName(request.getName());
            return academicYearRepository.save(year);
        }
        return null;
    }

    @Override
    public List<AcademicYearResponse> getAllAcademicYears() {
//        return 
    	List<AcademicYear> academicYears = (List<AcademicYear>) academicYearRepository.findAll();
    	return academicYears.stream().map(AcademicYearResponse::new).collect(Collectors.toList());
    }

    @Override
    public AcademicYearDetailResponse getAcademicYearById(int id) {
        AcademicYear academicYear = academicYearRepository.findById(id).orElse(null);
        if (academicYear == null) {
            return null;
        }

        AcademicYearDetailResponse response = new AcademicYearDetailResponse();
        response.setId(academicYear.getId());
        response.setName(academicYear.getName());
        response.setStartDate(academicYear.getStartDate());
        response.setEndDate(academicYear.getEndDate());

        // Map semesters
        List<AcademicYearDetailResponse.SemesterResponse> semesters = academicYear.getSemesters()
            .stream()
            .map(semester -> {
                AcademicYearDetailResponse.SemesterResponse sr = new AcademicYearDetailResponse.SemesterResponse();
                sr.setId(semester.getId());
                sr.setName(semester.getName());
                sr.setStartDate(semester.getStartDate());
                sr.setEndDate(semester.getEndDate());
                return sr;
            }).toList();

        response.setSemesters(semesters);
        return response;
    }


    @Override
    public void deleteAcademicYear(int id) {
        academicYearRepository.deleteById(id);
    }
}
