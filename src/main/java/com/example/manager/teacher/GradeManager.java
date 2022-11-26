package com.example.manager.teacher;

import com.example.dao.CourseDAO;
import com.example.dao.StudentCourseDAO;
import com.example.manager.BaseManager;
import com.example.model.entity.CourseEntity;
import com.example.model.entity.StudentCourseEntity;
import com.example.model.vo.response.table.TeacherGradeItemVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GradeManager extends BaseManager {

    private final CourseDAO courseDAO;
    private final StudentCourseDAO studentCourseDAO;

    public GradeManager(CourseDAO courseDAO, StudentCourseDAO studentCourseDAO) {
        this.courseDAO = courseDAO;
        this.studentCourseDAO = studentCourseDAO;
    }

    public Integer getTeacherGradePageCount(Long teacherId, String courseName, String studentName) {
        return calcPageCount(
                studentCourseDAO.countTeacherGrade(teacherId, courseName, studentName),
                StudentCourseDAO.PAGE_SIZE);
    }

    public List<TeacherGradeItemVO> getTeacherGradePage(
            Integer index,
            Long teacherId,
            String courseName,
            String studentName
    ) {
        return studentCourseDAO.getTeacherGradePage(index, teacherId, courseName, studentName);
    }

    public StudentCourseEntity getStudentCourseById(Long studentCourseId) {
        return studentCourseDAO.get(studentCourseId);
    }

    public CourseEntity getCourseById(Long courseId) {
        return courseDAO.get(courseId);
    }


    public void updateStudentCourse(StudentCourseEntity entity) {
        studentCourseDAO.update(entity);
    }
}
