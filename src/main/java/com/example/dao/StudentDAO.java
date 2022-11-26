package com.example.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dao.mapper.StudentMapper;
import com.example.model.vo.response.StudentInfoVO;
import com.example.model.entity.StudentEntity;
import com.example.model.vo.response.table.StudentItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAO extends BaseDAO {

    public static final int PAGE_SIZE = 20;
    private final StudentMapper mapper;

    public StudentDAO(StudentMapper mapper) {
        this.mapper = mapper;
    }

    public StudentEntity getByNumber(String number) {
        LambdaQueryWrapper<StudentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentEntity::getNumber, number);

        return mapper.selectOne(wrapper);
    }


    public int save(StudentEntity entity) {
        return mapper.insert(entity);
    }

    public int delete(Long id) {
        return mapper.deleteById(id);
    }

    public StudentEntity get(Long id) {
        return mapper.selectById(id);
    }

    public int update(StudentEntity entity) {
        return mapper.updateById(entity);
    }

    public int count(String majorName, String className, String name) {
        return mapper.count(majorName, className, name);
    }

    public List<StudentItemVO> getPage(Integer index, String majorName, String className, String name) {
        Page<StudentItemVO> page = new Page<>(index, PAGE_SIZE);

        return mapper.getPage(page, majorName, className, name).getRecords();
    }

    public Integer countByClassId(Long id) {
        LambdaQueryWrapper<StudentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentEntity::getClassId, id);

        return Math.toIntExact(mapper.selectCount(wrapper));
    }

    public List<StudentEntity> listName() {
        LambdaQueryWrapper<StudentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(StudentEntity::getId, StudentEntity::getName);

        return mapper.selectList(wrapper);
    }

    public Long getDepartmentIdById(Long studentId) {
        return mapper.getDepartmentIdById(studentId);
    }

    public Integer getGradeById(Long studentId) {
        return mapper.getGradeById(studentId);
    }

    public StudentInfoVO getStudentInfoById(Long studentId) {
        return mapper.getStudentInfoById(studentId);
    }
}
