package com.example.model.vo.request;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.example.model.constant.UserType.ADMIN;
import static com.example.model.constant.UserType.STUDENT;

@Data
public class LoginVO {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotNull(message = "必须选择用户类型")
    @Range(min = STUDENT, max = ADMIN, message = "无效的用户类型")
    private Integer userType;
}
