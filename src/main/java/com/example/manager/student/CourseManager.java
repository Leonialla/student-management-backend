package com.example.manager.student;

import com.example.dao.CourseDAO;
import com.example.dao.StudentCourseDAO;
import com.example.model.entity.StudentCourseEntity;
import com.example.model.vo.response.table.StudentCourseSelectedItemVO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("studentCourseManagerModule")
public class CourseManager {

    private final StudentCourseDAO studentCourseDAO;
    private final CourseDAO courseDAO;

    public CourseManager(StudentCourseDAO studentCourseDAO, CourseDAO courseDAO) {
        this.studentCourseDAO = studentCourseDAO;
        this.courseDAO = courseDAO;
    }

    public StudentCourseEntity getStudentCourseById(Long studentCourseId) {
        return studentCourseDAO.get(studentCourseId);
    }

    @Transactional
    public void deleteStudentCourse(StudentCourseEntity studentCourseEntity) {
        courseDAO.decreaseSelectedCount(studentCourseEntity.getCourseId());
        studentCourseDAO.delete(studentCourseEntity.getId());
    }

    public List<StudentCourseSelectedItemVO> listStudentCourseSelected(Long studentId) {
        return studentCourseDAO.listStudentCourseSelected(studentId);
    }
}
