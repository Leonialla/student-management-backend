package com.example.manager.admin;

import com.example.dao.CourseDAO;
import com.example.dao.StudentCourseDAO;
import com.example.dao.TeacherDAO;
import com.example.model.bo.CourseItemBO;
import com.example.model.vo.response.IdNameVO;
import com.example.manager.BaseManager;
import com.example.model.entity.CourseEntity;
import com.example.model.entity.TeacherEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseManager extends BaseManager {

    private final TeacherDAO teacherDAO;
    private final CourseDAO courseDAO;
    private final StudentCourseDAO studentCourseDAO;

    public CourseManager(TeacherDAO teacherDAO, CourseDAO courseDAO, StudentCourseDAO studentCourseDAO) {
        this.teacherDAO = teacherDAO;
        this.courseDAO = courseDAO;
        this.studentCourseDAO = studentCourseDAO;
    }

    public Integer getPageCount(String departmentName, String teacherName, String name) {
        int count = courseDAO.count(departmentName, teacherName, name);
        return calcPageCount(count, CourseDAO.PAGE_SIZE);
    }

    public List<CourseItemBO> getPage(Integer index, String departmentName, String teacherName, String name) {
        return courseDAO.getPage(index, departmentName, teacherName, name);
    }

    public CourseEntity get(Long id) {
        return courseDAO.get(id);
    }

    public CourseEntity getByName(String courseName) {
        return courseDAO.getByName(courseName);
    }

    public int create(CourseEntity entity) {
        return courseDAO.save(entity);
    }

    public int update(CourseEntity entity) {
        return courseDAO.update(entity);
    }

    public int delete(Long id) {
        return courseDAO.delete(id);
    }

    public TeacherEntity getTeacherById(Long teacherId) {
        return teacherDAO.get(teacherId);
    }

    public boolean hasStudentCourse(Long courseId) {
        return studentCourseDAO.countByCourseId(courseId) > 0;
    }

    public List<IdNameVO> listName() {
        List<IdNameVO> voList = new ArrayList<>();
        List<CourseEntity> entityList = courseDAO.listName();
        for (CourseEntity entity : entityList) {
            voList.add(new IdNameVO(entity.getId(), entity.getName()));
        }

        return voList;
    }
}
