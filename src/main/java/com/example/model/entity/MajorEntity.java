package com.example.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

@TableName("major")
@Data
public class MajorEntity {

    public static final String ID = "major_id";
    public static final String DEPARTMENT_ID = "major_department_id";
    public static final String NAME = "major_name";

    @TableId(value = ID, type = AUTO)
    private Long id;

    @NotNull(message = "必须选择所属系")
    @TableField(DEPARTMENT_ID)
    private Long departmentId;

    @NotBlank(message = "专业名不能为空")
    @TableField(NAME)
    private String name;
}
