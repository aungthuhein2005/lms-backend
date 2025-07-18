package com.lms.backend.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lms.backend.dto.CourseProgressDTO;
import com.lms.backend.dto.StudentCoursePorgressDTO;
import com.lms.backend.dto.TeacherCourseProgressDTO;
import com.lms.backend.entity.ClassEntity;
import com.lms.backend.entity.Course;
import com.lms.backend.entity.Enrollment;
import com.lms.backend.entity.Lesson;
import com.lms.backend.entity.StudentCompletedLesson;
import com.lms.backend.entity.StudentCourseKey;
import com.lms.backend.entity.StudentCourseStatus;
import com.lms.backend.repository.ClassRepository;
import com.lms.backend.repository.CourseRepository;
import com.lms.backend.repository.EnrollmentRepository;
import com.lms.backend.repository.LessonRepository;
import com.lms.backend.repository.ModuleRepository;
import com.lms.backend.repository.StudentCompletedLessonRepository;
import com.lms.backend.repository.StudentCourseStatusRepository;
import com.lms.backend.service.CourseProgressService;
import com.lms.backend.entity.Module;
import com.lms.backend.entity.Student;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseProgressServiceImpl implements CourseProgressService {

    private final StudentCompletedLessonRepository completedLessonRepo;
    private final StudentCourseStatusRepository courseStatusRepo;
    private final LessonRepository lessonRepo;
    private final ModuleRepository moduleRepo;
    private final CourseRepository courseRepo;
    private final ClassRepository classRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public void markLessonCompleted(Long studentId, Long lessonId) {
        // Prevent duplicates
        if (completedLessonRepo.existsByStudentIdAndLessonId(studentId, lessonId)) return;

        // Save completed lesson
        StudentCompletedLesson scl = new StudentCompletedLesson();
        scl.setStudentId(studentId);
        scl.setLessonId(lessonId);
        completedLessonRepo.save(scl);

        // Get courseId from lesson → module → course
        Lesson lesson = lessonRepo.findById(lessonId).orElseThrow();
        Module module = moduleRepo.findById(lesson.getModule().getId()).orElseThrow(null);
        int courseId = module.getCourse().getId();

        // Count total lessons in course
        List<Module> modules = moduleRepo.findByCourseId(courseId);
        List<Integer> allLessonIds = lessonRepo.findAllByModuleIdIn(
            modules.stream().map(Module::getId).toList()
        ).stream().map(Lesson::getId).toList();

        long total = allLessonIds.size();
        long completed = completedLessonRepo.countByStudentIdAndLessonIdIn(studentId, allLessonIds);

        double percent = (double) completed / total * 100;
        StudentCourseKey key = new StudentCourseKey(studentId, courseId);

        StudentCourseStatus status = courseStatusRepo.findById(key).orElse(new StudentCourseStatus());
        status.setId(key);
        status.setProgressPercent(percent);
        status.setUpdatedAt(LocalDateTime.now());

        if (percent == 100) {
            status.setStatus(StudentCourseStatus.Status.COMPLETED);
        } else {
            status.setStatus(StudentCourseStatus.Status.PROGRESS);
        }

        courseStatusRepo.save(status);
    }

    @Override
    public StudentCourseStatus getCourseStatus(Long studentId, int courseId) {
        StudentCourseKey key = new StudentCourseKey(studentId, courseId);
        return courseStatusRepo.findById(key).orElseThrow();
    }

    @Override
    public double calculateProgressPercent(Long studentId, int courseId) {
        // 1. Get all modules in the course
        List<Module> modules = moduleRepo.findByCourseId(courseId);
        List<Integer> moduleIds = modules.stream()
                .map(Module::getId)
                .collect(Collectors.toList());

        if (moduleIds.isEmpty()) return 0;

        // 2. Get all lessons in the modules
        List<Lesson> allLessons = lessonRepo.findAllByModuleIdIn(moduleIds);
        List<Integer> lessonIds = allLessons.stream()
                .map(Lesson::getId)
                .collect(Collectors.toList());

        if (lessonIds.isEmpty()) return 0;

        // 3. Count completed lessons by the student
        long completed = completedLessonRepo.countByStudentIdAndLessonIdIn(studentId, lessonIds);

        // 4. Calculate percentage
        double progressPercent = (double) completed / lessonIds.size() * 100;
        return Math.round(progressPercent * 10.0) / 10.0; // rounded to 1 decimal place
    }

	@Override
	public List<StudentCoursePorgressDTO> getStudentDashboardCourseProgress(int studentId) {
		// TODO Auto-generated method stub
		List<Enrollment> enrolledClasses = enrollmentRepository.findByStudentId(studentId);
		List<Course> enrolledCourses = enrolledClasses.stream()
													.map(cls->cls.getClassEntity().getCourse())
													.collect(Collectors.toList());
	    List<StudentCoursePorgressDTO> progressList = new ArrayList<>();

	    for (Course course : enrolledCourses) {
	        StudentCoursePorgressDTO dto = new StudentCoursePorgressDTO();
	        dto.setCourseId(course.getId());
	        dto.setCourseTitle(course.getTitle());

	        double percent = calculateProgressPercent((long) studentId, course.getId());
	        dto.setProgressPercent(percent);
	        dto.setCompleted(percent >= 100);

	        progressList.add(dto);
	    }
	    return progressList;
	}

	@Override
	public List<TeacherCourseProgressDTO> getTeacherDashboardCourseProgress(int teacherId) {
	    List<ClassEntity> classes = classRepository.findByTeacherId(teacherId);

	    Set<Course> courses = classes.stream()
	                                 .map(ClassEntity::getCourse)
	                                 .collect(Collectors.toSet());

	    List<TeacherCourseProgressDTO> result = new ArrayList<>();

	    for (Course course : courses) {
	        List<Lesson> lessons = lessonRepo.findByCourseId(course.getId());
	        List<Long> lessonIds = lessons.stream()
	                                      .map(lesson -> lesson.getId().longValue())
	                                      .collect(Collectors.toList());

	        if (lessonIds.isEmpty()) continue;

	        List<Enrollment> enrollments = enrollmentRepository.findByCourseId(course.getId());

	        Set<Integer> studentIds = enrollments.stream()
	                                          .map(en -> en.getStudent().getId())
	                                          .collect(Collectors.toSet());

	        double totalProgress = 0.0;

	        for (int studentId : studentIds) {
	            long completed = completedLessonRepo
	                    .countCompletedLessonsByStudent(studentId, lessonIds);

	            double studentProgress = ((double) completed / lessonIds.size()) * 100;
	            totalProgress += studentProgress;
	        }

	        double avgProgress = studentIds.isEmpty() ? 0 : totalProgress / studentIds.size();

	        TeacherCourseProgressDTO dto = new TeacherCourseProgressDTO();
	        dto.setCourseId(course.getId());
	        dto.setCourseTitle(course.getTitle());
	        dto.setAverageProgress(avgProgress);
	        dto.setStudentCount(studentIds.size());

	        result.add(dto);
	    }

	    return result;
	}





}
