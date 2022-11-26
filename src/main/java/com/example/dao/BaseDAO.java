package com.example.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

import static com.example.dao.DepartmentDAO.PAGE_SIZE;

public class BaseDAO {

    <T> List<T> selectPage(BaseMapper<T> mapper, LambdaQueryWrapper<T> wrapper, int index) {
        Page<T> page = new Page<>(index, PAGE_SIZE);
        return mapper.selectPage(page, wrapper).getRecords();
    }
}
