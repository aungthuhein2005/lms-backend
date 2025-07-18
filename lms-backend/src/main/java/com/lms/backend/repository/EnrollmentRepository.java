package com.lms.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lms.backend.dto.AcademicYearStudentCountDTO;
import com.lms.backend.entity.ClassEntity;
import com.lms.backend.entity.Enrollment;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Integer> {
	@Query("SELECT sc.classEntity FROM Enrollment sc WHERE sc.student.id = :studentId")
	List<ClassEntity> findClassesByStudentId(@Param("studentId") int studentId);
	List<Enrollment> findByStudentId(int studentId);
	List<Enrollment> findByClassEntityId(Integer classId);
	@Query("SELECT e FROM Enrollment e WHERE e.classEntity.course.id = :courseId")
	List<Enrollment> findByCourseId(@Param("courseId") int courseId);
	 @Query("SELECT e.classEntity.semester.academicYear.name AS academicYearName, COUNT(e.student.id) AS studentCount " +
	           "FROM Enrollment e " +
	           "GROUP BY e.classEntity.semester.academicYear.name")
	    List<AcademicYearStudentCountDTO> countStudentsByAcademicYear();
	 int countByClassEntity_Id(int classId);
}
