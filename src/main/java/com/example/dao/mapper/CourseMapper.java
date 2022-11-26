package com.example.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.model.bo.CourseItemBO;
import com.example.model.bo.StudentCourseSelectItemBO;
import com.example.model.entity.CourseEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMapper extends BaseMapper<CourseEntity> {

    Integer count(
            @Param("departmentName") String departmentName,
            @Param("teacherName") String teacherName,
            @Param("name") String name
    );

    IPage<CourseItemBO> getPage(
            IPage<CourseItemBO> page,
            @Param("departmentName") String departmentName,
            @Param("teacherName") String teacherName,
            @Param("name") String name
    );

    Integer countStudentCanSelect(
            @Param("studentId") Long studentId,
            @Param("departmentId") Long departmentId,
            @Param("grade") Integer grade,
            @Param("courseName") String courseName,
            @Param("teacherName") String teacherName
    );

    IPage<StudentCourseSelectItemBO> getStudentCanSelectPage(
            IPage<StudentCourseSelectItemBO> page,
            @Param("studentId") Long studentId,
            @Param("departmentId") Long departmentId,
            @Param("grade") Integer grade,
            @Param("courseName") String courseName,
            @Param("teacherName") String teacherName
    );

    Long getDepartmentIdById(Long courseId);
}
