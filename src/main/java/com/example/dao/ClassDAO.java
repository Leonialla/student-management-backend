package com.example.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dao.mapper.ClassMapper;
import com.example.model.entity.ClassEntity;
import com.example.model.vo.response.table.MajorItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClassDAO extends BaseDAO {

    public static final int PAGE_SIZE = 20;

    private final ClassMapper mapper;

    public ClassDAO(ClassMapper mapper) {
        this.mapper = mapper;
    }

    public int save(ClassEntity entity) {
        return mapper.insert(entity);
    }

    public int delete(Long id) {
        return mapper.deleteById(id);
    }

    public ClassEntity get(Long id) {
        return mapper.selectById(id);
    }

    public ClassEntity getByName(String className) {
        LambdaQueryWrapper<ClassEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ClassEntity::getName, className);
        return mapper.selectOne(wrapper);
    }

    public int update(ClassEntity entity) {
        return mapper.updateById(entity);
    }

    public int count(String departmentName, String majorName, String name) {
        return mapper.count(departmentName, majorName, name);
    }

    public List<MajorItemVO> getPage(Integer index, String departmentName, String majorName, String name) {
        Page<MajorItemVO> page = new Page<>(index, PAGE_SIZE);
        return mapper.getPage(page, departmentName, majorName, name).getRecords();
    }

    public Integer countByMajorId(Long id) {
        LambdaQueryWrapper<ClassEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ClassEntity::getMajorId, id);
        return Math.toIntExact(mapper.selectCount(wrapper));
    }

    public List<ClassEntity> listName() {
        LambdaQueryWrapper<ClassEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(ClassEntity::getId, ClassEntity::getName);
        return mapper.selectList(wrapper);
    }
}
