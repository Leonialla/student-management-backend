package com.example.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

import static com.baomidou.mybatisplus.annotation.FieldStrategy.IGNORED;
import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

@TableName("student_course")
@Data
public class StudentCourseEntity {

    public static final String ID = "sc_id";
    public static final String STUDENT_ID = "sc_student_id";
    public static final String COURSE_ID = "sc_course_id";
    public static final String DAILY_SCORE = "sc_daily_score";
    public static final String EXAM_SCORE = "sc_exam_score";
    public static final String SCORE = "sc_score";

    @TableId(value = ID, type = AUTO)
    private Long id;

    @NotNull(message = "必须选择学生")
    @TableField(STUDENT_ID)
    private Long studentId;

    @NotNull(message = "必须选择课程")
    @TableField(COURSE_ID)
    private Long courseId;

    @Range(min = 0, max = 100, message = "分数必须在0-100之间")
    @TableField(value = DAILY_SCORE, updateStrategy = IGNORED)
    private Integer dailyScore;

    @Range(min = 0, max = 100, message = "分数必须在0-100之间")
    @TableField(value = EXAM_SCORE, updateStrategy = IGNORED)
    private Integer examScore;

    @Range(min = 0, max = 100, message = "分数必须在0-100之间")
    @TableField(value = SCORE, updateStrategy = IGNORED)
    private Integer score;
}
