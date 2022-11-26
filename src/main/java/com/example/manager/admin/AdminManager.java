package com.example.manager.admin;

import com.example.dao.AdminDAO;
import com.example.manager.BaseManager;
import com.example.model.entity.AdminEntity;
import com.example.model.vo.response.IdNameVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminManager extends BaseManager {

    private final AdminDAO adminDAO;

    public AdminManager(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public AdminEntity get(Long id) {
        return adminDAO.get(id);
    }

    public AdminEntity getByUsername(String username) {
        return adminDAO.getByUsername(username);
    }

    public int create(AdminEntity entity) {
        return adminDAO.save(entity);
    }

    public int update(AdminEntity entity) {
        return adminDAO.update(entity);
    }

    protected List<IdNameVO> listName() {
        return null;
    }

    public int delete(Long id) {
        return adminDAO.delete(id);
    }

    public List<AdminEntity> list() {
        return adminDAO.list();
    }
}
