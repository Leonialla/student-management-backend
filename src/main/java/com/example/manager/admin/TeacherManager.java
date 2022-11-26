package com.example.manager.admin;

import com.example.dao.CourseDAO;
import com.example.dao.DepartmentDAO;
import com.example.dao.TeacherDAO;
import com.example.model.vo.response.IdNameVO;
import com.example.model.vo.response.table.TeacherItemVO;
import com.example.manager.BaseManager;
import com.example.model.entity.DepartmentEntity;
import com.example.model.entity.TeacherEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherManager extends BaseManager {

    private final DepartmentDAO departmentDAO;
    private final TeacherDAO teacherDAO;
    private final CourseDAO courseDAO;

    public TeacherManager(DepartmentDAO departmentDAO, TeacherDAO teacherDAO, CourseDAO courseDAO) {
        this.departmentDAO = departmentDAO;
        this.teacherDAO = teacherDAO;
        this.courseDAO = courseDAO;
    }

    public Integer getPageCount(String departmentName, String name) {
        int count = teacherDAO.count(departmentName, name);
        return calcPageCount(count, TeacherDAO.PAGE_SIZE);
    }

    public List<TeacherItemVO> getPage(Integer index, String departmentName, String name) {
        return teacherDAO.getPage(index, departmentName, name);
    }

    public TeacherEntity get(Long id) {
        return teacherDAO.get(id);
    }

    public TeacherEntity getByNumber(String number) {
        return teacherDAO.getByNumber(number);
    }

    public int create(TeacherEntity entity) {
        return teacherDAO.save(entity);
    }

    public int update(TeacherEntity entity) {
        return teacherDAO.update(entity);
    }

    public int delete(Long id) {
        return teacherDAO.delete(id);
    }

    public boolean hasCourse(Long teacherId) {
        return courseDAO.countByTeacherId(teacherId) > 0;
    }

    public DepartmentEntity getDepartmentById(Long id) {
        return departmentDAO.get(id);
    }

    public List<IdNameVO> listName() {
        List<IdNameVO> voList = new ArrayList<>();
        List<TeacherEntity> entityList = teacherDAO.listName();
        for (TeacherEntity entity : entityList) {
            voList.add(new IdNameVO(entity.getId(), entity.getName()));
        }
        return voList;
    }
}
