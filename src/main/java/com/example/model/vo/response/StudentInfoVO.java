package com.example.model.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class StudentInfoVO {

    private String number;
    private String name;
    private String departmentName;
    private String majorName;
    private String className;
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private Integer sex;
}
