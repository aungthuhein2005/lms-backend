package com.lms.backend.service;

import java.util.List;

import com.lms.backend.dto.AcademicYearDetailResponse;
import com.lms.backend.dto.AcademicYearRequest;
import com.lms.backend.dto.AcademicYearResponse;
import com.lms.backend.entity.AcademicYear;

public interface AcademicYearService {
	AcademicYear createAcademicYear(AcademicYearRequest request);
	AcademicYear updateAcademicYear(int id,AcademicYearRequest request);
	List<AcademicYearResponse> getAllAcademicYears();
	AcademicYearDetailResponse getAcademicYearById(int id);
	void deleteAcademicYear(int id);
}
