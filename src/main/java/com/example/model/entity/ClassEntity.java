package com.example.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

@TableName("class")
@Data
public class ClassEntity {

    public static final String ID = "class_id";
    public static final String MAJOR_ID = "class_major_id";
    public static final String GRADE = "class_grade";
    public static final String NAME = "class_name";

    @TableId(value = ID, type = AUTO)
    private Long id;

    @NotNull(message = "必须选择所属专业")
    @TableField(MAJOR_ID)
    private Long majorId;

    @NotNull
    @Range(min = 1980, max = 2999, message = "年级范围必须在1980-2999之间")
    @TableField(GRADE)
    private Integer grade;

    @NotBlank(message = "班级名不能为空")
    @TableField(NAME)
    private String name;
}
