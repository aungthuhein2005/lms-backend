package com.lms.backend.dto;

import lombok.Data;

@Data
public class AssignRequest {
    private Integer teacherId;
    private Integer classId;
    private String assigned_at;
}
