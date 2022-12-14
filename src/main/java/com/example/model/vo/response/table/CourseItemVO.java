package com.example.model.vo.response.table;

import lombok.Data;

@Data
public class CourseItemVO {

    private Long id;
    private String name;
    private String teacherName;
    private String departmentName;
    private Integer grade;
    private Integer credit;
    private String time;
    private Integer selectedCount;
    private Integer maxSize;
}
