package com.example.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;
import static com.example.config.permission.annotation.Admin.ALL;
import static com.example.config.permission.annotation.Admin.NO;

@TableName("admin")
@Data
public class AdminEntity {

    public static final String ID = "admin_id";
    public static final String USERNAME = "admin_username";
    public static final String PASSWORD = "admin_password";
    public static final String PRIVILEGE = "admin_privilege";

    @TableId(value = ID, type = AUTO)
    private Long id;

    @NotBlank(message = "用户名不能为空")
    @TableField(USERNAME)
    private String username;

    @NotNull
    @TableField(PASSWORD)
    private String password;

    @NotNull
    @Range(min = NO, max = ALL)
    @TableField(PRIVILEGE)
    private Integer privilege;
}
