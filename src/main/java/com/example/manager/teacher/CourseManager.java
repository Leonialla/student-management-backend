package com.example.manager.teacher;

import com.example.dao.TeacherDAO;
import com.example.manager.BaseManager;
import com.example.model.vo.response.table.TeacherCourseItemVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("teacherCourseManager")
public class CourseManager extends BaseManager {

    private final TeacherDAO teacherDAO;

    public CourseManager(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    public List<TeacherCourseItemVO> listTeacherCourse(Long teacherId) {
        return teacherDAO.listTeacherCourse(teacherId);
    }
}
