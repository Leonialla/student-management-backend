package com.example.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

@TableName("teacher")
@Data
public class TeacherEntity {

    public static final String ID = "teacher_id";
    public static final String DEPARTMENT_ID = "teacher_department_id";
    public static final String NUMBER = "teacher_number";
    public static final String NAME = "teacher_name";
    public static final String PASSWORD = "teacher_password";

    @TableId(value = ID, type = AUTO)
    private Long id;

    @NotNull(message = "必须选择所属系")
    @TableField(DEPARTMENT_ID)
    private Long departmentId;

    @Pattern(regexp = "\\d{12}", message = "工号必须为12位纯数字")
    @TableField(NUMBER)
    private String number;

    @NotBlank(message = "教师姓名不能为空")
    @TableField(NAME)
    private String name;

    @NotNull
    @TableField(PASSWORD)
    private String password;
}
