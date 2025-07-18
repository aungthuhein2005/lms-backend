package com.lms.backend.dto;

import lombok.Data;

@Data
public class ClassDTO {
    private int id;
    private String name;
    private String description;
    
    public ClassDTO(com.lms.backend.entity.ClassEntity cls) {
        this.id = cls.getId();
        this.name = cls.getName();
        this.description = cls.getDescription(); // adapt to your structure
    }

}
