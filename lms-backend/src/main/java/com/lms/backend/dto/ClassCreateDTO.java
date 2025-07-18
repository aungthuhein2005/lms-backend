package com.lms.backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class ClassCreateDTO {
    private String name;
    private String description;
    private String schedule; // optional (you can remove it if no longer needed)
    private int semester_id;
    private int teacher_id;
    private int course_id;

    private List<ScheduleDTO> schedules;

    @Data
    public static class ScheduleDTO {
        private String dayOfWeek;   // e.g., "MONDAY"
        private String startTime;   // e.g., "09:00"
        private String endTime;     // e.g., "11:00"
    }
}
