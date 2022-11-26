package com.example.manager.student;

import com.example.dao.CourseDAO;
import com.example.dao.StudentCourseDAO;
import com.example.dao.StudentDAO;
import com.example.model.bo.StudentCourseSelectItemBO;
import com.example.manager.BaseManager;
import com.example.model.entity.CourseEntity;
import com.example.model.entity.StudentCourseEntity;
import com.example.model.entity.StudentEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.dao.StudentCourseDAO.PAGE_SIZE;

@Component
public class CourseSelectManager extends BaseManager {
    private final CourseDAO courseDAO;
    private final StudentDAO studentDAO;
    private final StudentCourseDAO studentCourseDAO;

    public CourseSelectManager(
            CourseDAO courseDAO,
            StudentDAO studentDAO,
            StudentCourseDAO studentCourseDAO
    ) {
        this.courseDAO = courseDAO;
        this.studentDAO = studentDAO;
        this.studentCourseDAO = studentCourseDAO;
    }

    public Integer getPageCount(
            Long studentId,
            String courseName,
            String teacherName
    ) {
        Long departmentId = studentDAO.getDepartmentIdById(studentId);
        Integer grade = studentDAO.getGradeById(studentId);
        return calcPageCount(
                courseDAO.countStudentCanSelect(departmentId, studentId, grade, courseName, teacherName),
                PAGE_SIZE
        );
    }

    public List<StudentCourseSelectItemBO> getPage(
            Integer index,
            Long studentId,
            String courseName,
            String teacherName
    ) {
        Long departmentId = studentDAO.getDepartmentIdById(studentId);
        Integer grade = studentDAO.getGradeById(studentId);
        return courseDAO.getStudentCanSelectPage(index, departmentId, studentId, grade, courseName, teacherName);
    }

    public CourseEntity getCourseById(Long courseId) {
        return courseDAO.get(courseId);
    }

    public StudentEntity getStudentById(Long studentId) {
        return studentDAO.get(studentId);
    }

    public boolean inSameDepartment(Long courseId, Long studentId) {
        return courseDAO.getDepartmentIdById(courseId).equals(studentDAO.getDepartmentIdById(studentId));
    }

    public StudentCourseEntity getStudentCourseByCourseIdAndStudentId(Long courseId, Long studentId) {
        return studentCourseDAO.getByCourseIdAndStudentId(courseId, studentId);
    }

    public Integer getStudentGradeById(Long studentId) {
        return studentDAO.getGradeById(studentId);
    }

    @Transactional
    public int create(StudentCourseEntity entity) {
        courseDAO.increaseSelectedCount(entity.getCourseId());
        return studentCourseDAO.save(entity);
    }

    public int countStudentCourseSelectedByTimePart(Long studentId, String timePart) {
        return studentCourseDAO.countStudentCourseSelectedByTimePart(studentId, timePart);
    }
}
