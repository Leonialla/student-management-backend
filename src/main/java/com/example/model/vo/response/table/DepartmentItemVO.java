package com.example.model.vo.response.table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentItemVO {

    private Long id;
    private String name;
    private Integer majorCount;
    private Integer teacherCount;
}
