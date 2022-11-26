package com.example.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dao.mapper.MajorMapper;
import com.example.model.entity.MajorEntity;
import com.example.model.vo.response.table.MajorItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MajorDAO extends BaseDAO {

    public static final int PAGE_SIZE = 20;

    private final MajorMapper mapper;

    public MajorDAO(MajorMapper mapper) {
        this.mapper = mapper;
    }

    public int save(MajorEntity entity) {
        return mapper.insert(entity);
    }

    public int delete(Long id) {
        return mapper.deleteById(id);
    }

    public MajorEntity get(Long id) {
        return mapper.selectById(id);
    }

    public MajorEntity getByName(String majorName) {
        LambdaQueryWrapper<MajorEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MajorEntity::getName, majorName);
        return mapper.selectOne(wrapper);
    }

    public int update(MajorEntity entity) {
        return mapper.updateById(entity);
    }

    public int count(String departmentName, String name) {
        return mapper.count(departmentName, name);
    }

    public List<MajorItemVO> getPage(Integer index, String departmentName, String name) {
        Page<MajorItemVO> page = new Page<>(index, PAGE_SIZE);
        return mapper.getPage(page, departmentName, name).getRecords();
    }

    public Integer countByDepartmentId(Long departmentId) {
        LambdaQueryWrapper<MajorEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MajorEntity::getDepartmentId, departmentId);
        return Math.toIntExact(mapper.selectCount(wrapper));
    }

    public List<MajorEntity> listName() {
        LambdaQueryWrapper<MajorEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(MajorEntity::getId, MajorEntity::getName);
        return mapper.selectList(wrapper);
    }
}
