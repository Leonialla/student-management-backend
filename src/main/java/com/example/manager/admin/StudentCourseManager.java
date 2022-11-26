package com.example.manager.admin;

import com.example.dao.CourseDAO;
import com.example.dao.StudentCourseDAO;
import com.example.dao.StudentDAO;
import com.example.manager.BaseManager;
import com.example.model.entity.CourseEntity;
import com.example.model.entity.StudentCourseEntity;
import com.example.model.entity.StudentEntity;
import com.example.model.vo.response.IdNameVO;
import com.example.model.vo.response.table.StudentCourseItemVO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class StudentCourseManager extends BaseManager {

    private final CourseDAO courseDAO;
    private final StudentCourseDAO studentCourseDAO;
    private final StudentDAO studentDAO;

    public StudentCourseManager(
            CourseDAO courseDAO,
            StudentCourseDAO studentCourseDAO,
            StudentDAO studentDAO
    ) {
        this.courseDAO = courseDAO;
        this.studentCourseDAO = studentCourseDAO;
        this.studentDAO = studentDAO;
    }

    public Integer getPageCount(String className, String courseName, String studentName) {
        int count = studentCourseDAO.count(className, courseName, studentName);
        return calcPageCount(count, StudentCourseDAO.PAGE_SIZE);
    }

    public List<StudentCourseItemVO> getPage(
            Integer index,
            String className,
            String courseName,
            String studentName
    ) {
        return studentCourseDAO.getPage(index, className, courseName, studentName);
    }

    public StudentCourseEntity get(Long id) {
        return studentCourseDAO.get(id);
    }

    public int delete(Long id) {
        return studentCourseDAO.delete(id);
    }

    @Transactional
    public int create(StudentCourseEntity entity) {
        courseDAO.increaseSelectedCount(entity.getCourseId());
        return studentCourseDAO.save(entity);
    }

    public int update(StudentCourseEntity entity) {
        return studentCourseDAO.update(entity);
    }

    protected List<IdNameVO> listName() {
        return null;
    }

    @Transactional
    public int delete(StudentCourseEntity entity) {
        courseDAO.decreaseSelectedCount(entity.getCourseId());
        return studentCourseDAO.delete(entity.getId());
    }

    public CourseEntity getCourseById(Long courseId) {
        return courseDAO.get(courseId);
    }

    public StudentEntity getStudentById(Long studentId) {
        return studentDAO.get(studentId);
    }

    public StudentCourseEntity getByCourseIdAndStudentId(Long courseId, Long studentId) {
        return studentCourseDAO.getByCourseIdAndStudentId(courseId, studentId);
    }

    public Integer getStudentGradeById(Long studentId) {
        return studentDAO.getGradeById(studentId);
    }

    public boolean inSameDepartment(Long courseId, Long studentId) {
        return courseDAO.getDepartmentIdById(courseId).equals(studentDAO.getDepartmentIdById(studentId));
    }
}
