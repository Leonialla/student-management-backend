package com.example.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.model.vo.response.table.StudentCourseItemVO;
import com.example.model.vo.response.table.StudentCourseSelectedItemVO;
import com.example.model.vo.response.table.StudentExamItemVO;
import com.example.model.entity.StudentCourseEntity;
import com.example.model.vo.response.table.TeacherGradeItemVO;
import com.example.model.vo.response.table.TimetableItemVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseMapper extends BaseMapper<StudentCourseEntity> {

    Integer count(
            @Param("className") String className,
            @Param("courseName") String courseName,
            @Param("studentName") String studentName
    );

    IPage<StudentCourseItemVO> getPage(
            IPage<StudentCourseItemVO> page,
            @Param("className") String className,
            @Param("courseName") String courseName,
            @Param("studentName") String studentName
    );

    Integer countTeacherGrade(
            @Param("teacherId") Long teacherId,
            @Param("courseName") String courseName,
            @Param("studentName") String studentName
    );

    IPage<TeacherGradeItemVO> getTeacherGradePage(
            IPage<TeacherGradeItemVO> page,
            @Param("teacherId") Long teacherId,
            @Param("courseName") String courseName,
            @Param("studentName") String studentName
    );

    List<StudentCourseSelectedItemVO> listStudentCourseSelected(Long studentId);

    List<StudentExamItemVO> listStudentExam(Long studentId);

    Integer countStudentCourseSelectedByTimePart(
            @Param("studentId") Long studentId,
            @Param("timePart") String timePart
    );

    List<TimetableItemVO> listStudentTimetable(Long studentId);
}
