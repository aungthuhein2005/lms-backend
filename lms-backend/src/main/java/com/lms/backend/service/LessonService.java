package com.lms.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lms.backend.entity.Lesson;
import com.lms.backend.repository.LessonRepository;


@Service
public class LessonService {
	
    @Autowired
    private LessonRepository lessonRepository;

    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public List<Lesson> getLessonsByModuleId(Integer moduleId) {
        return lessonRepository.findByModuleId(moduleId);
    }

    public Optional<Lesson> getLessonById(Integer id) {
        return lessonRepository.findById(id);
    }

    public Lesson updateLesson(Integer id, Lesson updatedlesson) {
        Lesson lesson = lessonRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Lesson not found"));
        lesson.setTitle(updatedlesson.getTitle());
        lesson.setMediaURL(updatedlesson.getMediaURL());
        return lessonRepository.save(lesson);
    }

    public void deleteLesson(Integer id) {
        lessonRepository.deleteById(id);
    }
}
