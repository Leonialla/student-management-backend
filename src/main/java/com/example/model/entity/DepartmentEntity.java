package com.example.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;


@TableName("department")
@Data
public class DepartmentEntity {

    public static final String ID = "department_id";
    public static final String NAME = "department_name";

    @TableId(value = ID, type = AUTO)
    private Long id;

    @NotBlank(message = "系名不能为空")
    @TableField(NAME)
    private String name;
}
