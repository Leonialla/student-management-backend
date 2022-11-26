package com.example.model.vo.response.table;

import lombok.Data;

@Data
public class TeacherGradeItemVO {

    private Long studentCourseId;
    private String courseName;
    private String studentName;
    private Integer dailyScore;
    private Integer examScore;
    private Integer score;
}
