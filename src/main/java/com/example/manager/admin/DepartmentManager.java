package com.example.manager.admin;

import com.example.dao.DepartmentDAO;
import com.example.dao.MajorDAO;
import com.example.dao.TeacherDAO;
import com.example.model.vo.response.IdNameVO;
import com.example.model.vo.response.table.DepartmentItemVO;
import com.example.manager.BaseManager;
import com.example.model.entity.DepartmentEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentManager extends BaseManager {

    private final DepartmentDAO departmentDAO;
    private final TeacherDAO teacherDAO;
    private final MajorDAO majorDAO;

    public DepartmentManager(DepartmentDAO departmentDAO, TeacherDAO teacherDAO, MajorDAO majorDAO) {
        this.departmentDAO = departmentDAO;
        this.teacherDAO = teacherDAO;
        this.majorDAO = majorDAO;
    }

    public Integer getPageCount(String name) {
        int count = departmentDAO.count(name);
        return calcPageCount(count, DepartmentDAO.PAGE_SIZE);
    }

    @Transactional
    public List<DepartmentItemVO> getPage(Integer index, String namePart) {
        List<DepartmentItemVO> departmentItemList = new ArrayList<>();
        List<DepartmentEntity> departmentList = departmentDAO.getPage(index, namePart);

        for (DepartmentEntity department : departmentList) {
            long id = department.getId();
            String name = department.getName();
            int majorCount = majorDAO.countByDepartmentId(id);
            int teacherCount = teacherDAO.countByDepartmentId(id);

            departmentItemList.add(new DepartmentItemVO(id, name, majorCount, teacherCount));
        }

        return departmentItemList;
    }

    public DepartmentEntity get(Long id) {
        return departmentDAO.get(id);
    }

    public DepartmentEntity getByName(String departmentName) {
        return departmentDAO.getByName(departmentName);
    }

    public int create(DepartmentEntity entity) {
        return departmentDAO.save(entity);
    }

    public int update(DepartmentEntity entity) {
        return departmentDAO.update(entity);
    }

    public int delete(Long id) {
        return departmentDAO.delete(id);
    }

    public boolean hasMajor(Long id) {
        return majorDAO.countByDepartmentId(id) > 0;
    }

    public boolean hasTeacher(Long id) {
        return teacherDAO.countByDepartmentId(id) > 0;
    }

    public List<IdNameVO> listName() {
        List<IdNameVO> voList = new ArrayList<>();
        List<DepartmentEntity> entityList = departmentDAO.listName();
        for (DepartmentEntity entity : entityList) {
            voList.add(new IdNameVO(entity.getId(), entity.getName()));
        }
        return voList;
    }
}
