package com.example.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.model.vo.response.table.TeacherItemVO;
import com.example.model.entity.TeacherEntity;
import com.example.model.vo.response.table.TeacherCourseItemVO;
import com.example.model.vo.response.table.TimetableItemVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper extends BaseMapper<TeacherEntity> {
    Integer count(@Param("departmentName") String departmentName, @Param("name") String name);

    IPage<TeacherItemVO> getPage(
            IPage<TeacherItemVO> page,
            @Param("departmentName") String departmentName,
            @Param("name") String name
    );

    List<TimetableItemVO> listTeacherTimetable(Long teacherId);

    List<TeacherCourseItemVO> listTeacherCourse(Long teacherId);
}
