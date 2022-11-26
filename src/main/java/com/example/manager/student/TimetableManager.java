package com.example.manager.student;

import com.example.dao.StudentCourseDAO;
import com.example.manager.BaseManager;
import com.example.model.vo.response.table.TimetableItemVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("student_timetableManager")
public class TimetableManager extends BaseManager {

    private final StudentCourseDAO studentCourseDAO;

    public TimetableManager(StudentCourseDAO studentCourseDAO) {
        this.studentCourseDAO = studentCourseDAO;
    }

    public List<TimetableItemVO> listStudentTimetable(Long studentId) {
        return studentCourseDAO.listStudentTimetable(studentId);
    }
}
