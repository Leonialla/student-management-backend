package com.example.manager.admin;

import com.example.dao.ClassDAO;
import com.example.dao.DepartmentDAO;
import com.example.dao.MajorDAO;
import com.example.model.vo.response.IdNameVO;
import com.example.manager.BaseManager;
import com.example.model.entity.DepartmentEntity;
import com.example.model.entity.MajorEntity;
import com.example.model.vo.response.table.MajorItemVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MajorManager extends BaseManager {

    private final MajorDAO majorDAO;
    private final DepartmentDAO departmentDAO;
    private final ClassDAO classDAO;

    public MajorManager(MajorDAO majorDAO, DepartmentDAO departmentDAO, ClassDAO classDAO) {
        this.majorDAO = majorDAO;
        this.departmentDAO = departmentDAO;
        this.classDAO = classDAO;
    }

    public Integer getPageCount(String departmentName, String name) {
        int count = majorDAO.count(departmentName, name);
        return calcPageCount(count, MajorDAO.PAGE_SIZE);
    }

    public List<MajorItemVO> getPage(Integer index, String departmentName, String name) {
        return majorDAO.getPage(index, departmentName, name);
    }

    public MajorEntity get(Long id) {
        return majorDAO.get(id);
    }

    public MajorEntity getByName(String majorName) {
        return majorDAO.getByName(majorName);
    }

    public int create(MajorEntity entity) {
        return majorDAO.save(entity);
    }

    public int update(MajorEntity entity) {
        return majorDAO.update(entity);
    }

    public int delete(Long id) {
        return majorDAO.delete(id);
    }

    public boolean hasClass(Long majorId) {
        return classDAO.countByMajorId(majorId) > 0;
    }

    public DepartmentEntity getDepartmentById(Long id) {
        return departmentDAO.get(id);
    }

    public List<IdNameVO> listName() {
        List<IdNameVO> voList = new ArrayList<>();
        List<MajorEntity> entityList = majorDAO.listName();
        for (MajorEntity entity : entityList) {
            voList.add(new IdNameVO(entity.getId(), entity.getName()));
        }

        return voList;
    }
}
