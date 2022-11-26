package com.example.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

import static com.baomidou.mybatisplus.annotation.FieldStrategy.IGNORED;
import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

@TableName("student")
@Data
public class StudentEntity {

    public static final String ID = "student_id";
    public static final String CLASS_ID = "student_class_id";
    public static final String NUMBER = "student_number";
    public static final String NAME = "student_name";
    public static final String PASSWORD = "student_password";
    public static final String EMAIL = "student_email";
    public static final String BIRTHDAY = "student_birthday";
    public static final String SEX = "student_sex";
    public static final String LAST_LOGIN_TIME = "student_last_login_time";

    @TableId(value = ID, type = AUTO)
    private Long id;

    @NotNull(message = "必须选择所属班级")
    @TableField(CLASS_ID)
    private Long classId;

    @Pattern(regexp = "\\d{12}", message = "学号必须为12位纯数字")
    @TableField(NUMBER)
    private String number;

    @NotBlank(message = "学生姓名不能为空")
    @TableField(NAME)
    private String name;

    @NotNull
    @TableField(PASSWORD)
    private String password;

    @Email(message = "邮箱格式不正确")
    @TableField(value = EMAIL, updateStrategy = IGNORED)
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = BIRTHDAY, updateStrategy = IGNORED)
    private Date birthday;

    @Range(min = 0, max = 1)
    @TableField(SEX)
    private Integer sex;

    @TableField(LAST_LOGIN_TIME)
    private Date lastLoginTime;
}
