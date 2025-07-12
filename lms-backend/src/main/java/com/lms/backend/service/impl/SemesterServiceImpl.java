package com.lms.backend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.backend.dto.SemesterRequest;
import com.lms.backend.entity.AcademicYear;
import com.lms.backend.entity.Semester;
import com.lms.backend.repository.AcademicYearRepository;
import com.lms.backend.repository.SemesterRepository;
import com.lms.backend.service.SemesterService;

@Service
public class SemesterServiceImpl implements SemesterService {

    private final SemesterRepository semesterRepository;
    private final AcademicYearRepository academicYearRepository;

    @Autowired
    public SemesterServiceImpl(SemesterRepository semesterRepository,
                               AcademicYearRepository academicYearRepository) {
        this.semesterRepository = semesterRepository;
        this.academicYearRepository = academicYearRepository;
    }

    @Override
    public List<Semester> getAllSemesters() {
        return (List<Semester>) semesterRepository.findAll();
    }

    @Override
    public Semester getSemesterById(int id) {
        return semesterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Semester not found with id: " + id));
    }

    @Override
    public void deleteSemester(int id) {
        if (!semesterRepository.existsById(id)) {
            throw new RuntimeException("Semester not found with id: " + id);
        }
        semesterRepository.deleteById(id);
    }

    @Override
    public Semester updateSemester(int id,SemesterRequest request) {
        Semester semester = semesterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Semester not found with id: " + id));

        semester.setName(request.getName());
        semester.setStartDate(request.getStart_date());
        semester.setEndDate(request.getEnd_date());

        if (request.getAcademic_year_id() != null) {
        	AcademicYear academicYear = academicYearRepository.findById(request.getAcademic_year_id())
        		    .orElseThrow(() -> new RuntimeException("AcademicYear not found with id: " + request.getAcademic_year_id()));

            semester.setAcademicYear(academicYear);
        }

        return semesterRepository.save(semester);
    }

    @Override
    public Semester createSemester(SemesterRequest request) {
        Semester semester = new Semester();
        semester.setName(request.getName());
        semester.setStartDate(request.getStart_date());
        semester.setEndDate(request.getEnd_date());

        if (request.getAcademic_year_id() != null) {
            AcademicYear academicYear = academicYearRepository.findById(request.getAcademic_year_id())
                    .orElseThrow(() -> new RuntimeException("Academic Year not found with id: " + request.getAcademic_year_id()));
            semester.setAcademicYear(academicYear);
        }

        return semesterRepository.save(semester);
    }

	@Override
	public List<Semester> getSemestersByAcademicYear(int yearId) {
		// TODO Auto-generated method stub
		List<Semester> semesters = semesterRepository.findByAcademicYear_Id(yearId);
		return semesters;
	}


}
