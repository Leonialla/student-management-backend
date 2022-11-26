package com.example.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.dao.mapper.DepartmentMapper;
import com.example.model.entity.DepartmentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDAO extends BaseDAO {

    public static final int PAGE_SIZE = 20;

    private final DepartmentMapper mapper;

    public DepartmentDAO(DepartmentMapper mapper) {
        this.mapper = mapper;
    }

    public int save(DepartmentEntity entity) {
        return mapper.insert(entity);
    }

    public int delete(Long id) {
        return mapper.deleteById(id);
    }

    public DepartmentEntity get(Long id) {
        return mapper.selectById(id);
    }

    public DepartmentEntity getByName(String departmentName) {
        LambdaQueryWrapper<DepartmentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DepartmentEntity::getName, departmentName);
        return mapper.selectOne(wrapper);
    }

    public int update(DepartmentEntity entity) {
        return mapper.updateById(entity);
    }

    public int count(String name) {
        LambdaQueryWrapper<DepartmentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, DepartmentEntity::getName, name);
        return Math.toIntExact(mapper.selectCount(wrapper));
    }

    public List<DepartmentEntity> getPage(Integer index, String name) {
        LambdaQueryWrapper<DepartmentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, DepartmentEntity::getName, name);
        return selectPage(mapper, wrapper, index);
    }

    public List<DepartmentEntity> listName() {
        LambdaQueryWrapper<DepartmentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(DepartmentEntity::getId, DepartmentEntity::getName);
        return mapper.selectList(wrapper);
    }
}
