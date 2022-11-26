package com.example.manager.teacher;

import com.example.dao.TeacherDAO;
import com.example.manager.BaseManager;
import com.example.model.vo.response.table.TimetableItemVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TimetableManager extends BaseManager {

    private final TeacherDAO teacherDAO;

    public TimetableManager(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    public List<TimetableItemVO> listTeacherTimetable(Long teacherId) {
        return teacherDAO.listTeacherTimetable(teacherId);
    }
}
