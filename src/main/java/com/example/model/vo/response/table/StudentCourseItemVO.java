package com.example.model.vo.response.table;

import lombok.Data;

@Data
public class StudentCourseItemVO {

    private Long id;
    private String courseName;
    private String studentName;
    private String className;
    private Integer dailyScore;
    private Integer examScore;
    private Integer score;
}
