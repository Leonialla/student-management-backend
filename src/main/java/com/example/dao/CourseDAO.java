package com.example.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dao.mapper.CourseMapper;
import com.example.model.bo.CourseItemBO;
import com.example.model.bo.StudentCourseSelectItemBO;
import com.example.model.entity.CourseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDAO extends BaseDAO {

    public static final int PAGE_SIZE = 20;

    private final CourseMapper mapper;

    public CourseDAO(CourseMapper mapper) {
        this.mapper = mapper;
    }

    public int save(CourseEntity entity) {
        return mapper.insert(entity);
    }

    public int delete(Long id) {
        return mapper.deleteById(id);
    }

    public CourseEntity get(Long id) {
        return mapper.selectById(id);
    }

    public CourseEntity getByName(String courseName) {
        LambdaQueryWrapper<CourseEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CourseEntity::getName, courseName);
        return mapper.selectOne(wrapper);
    }

    public int update(CourseEntity entity) {
        return mapper.updateById(entity);
    }

    public int count(String departmentName, String teacherName, String name) {
        return mapper.count(departmentName, teacherName, name);
    }

    public List<CourseItemBO> getPage(
            Integer index,
            String departmentName,
            String teacherName,
            String name
    ) {
        Page<CourseItemBO> page = new Page<>(index, PAGE_SIZE);
        return mapper.getPage(page, departmentName, teacherName, name).getRecords();
    }

    public Integer countByTeacherId(Long teacherId) {
        LambdaQueryWrapper<CourseEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CourseEntity::getTeacherId, teacherId);

        return Math.toIntExact(mapper.selectCount(wrapper));
    }

    public List<CourseEntity> listName() {
        LambdaQueryWrapper<CourseEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(CourseEntity::getId, CourseEntity::getName);

        return mapper.selectList(wrapper);
    }

    public void increaseSelectedCount(Long courseId) {
        CourseEntity course = mapper.selectById(courseId);
        course.setSelectedCount(course.getSelectedCount() + 1);

        mapper.updateById(course);
    }

    public void decreaseSelectedCount(Long courseId) {
        CourseEntity course = mapper.selectById(courseId);
        course.setSelectedCount(course.getSelectedCount() - 1);

        mapper.updateById(course);
    }

    public Integer countStudentCanSelect(Long studentId,
                                         Long departmentId,
                                         Integer grade,
                                         String courseName,
                                         String teacherName
    ) {
        return mapper.countStudentCanSelect(
                studentId,
                departmentId,
                grade,
                courseName,
                teacherName
        );
    }

    public List<StudentCourseSelectItemBO> getStudentCanSelectPage(
            Integer index,
            Long studentId,
            Long departmentId,
            Integer grade,
            String courseName,
            String teacherName
    ) {
        Page<StudentCourseSelectItemBO> page = new Page<>(index, PAGE_SIZE);
        return mapper.getStudentCanSelectPage(
                page,
                studentId,
                departmentId,
                grade,
                courseName,
                teacherName
        ).getRecords();
    }

    public Long getDepartmentIdById(Long courseId) {
        return mapper.getDepartmentIdById(courseId);
    }
}
