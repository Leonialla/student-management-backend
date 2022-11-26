package com.example.model.bo;

import lombok.Data;

@Data
public class StudentCourseSelectItemBO {

    private Long courseId;
    private String courseName;
    private String teacherName;
    private Integer credit;
    private String time;
    private Integer selectedCount;
    private Integer maxSize;
}
