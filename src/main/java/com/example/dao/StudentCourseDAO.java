package com.example.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dao.mapper.StudentCourseMapper;
import com.example.model.vo.response.table.StudentCourseItemVO;
import com.example.model.vo.response.table.StudentCourseSelectedItemVO;
import com.example.model.vo.response.table.StudentExamItemVO;
import com.example.model.entity.StudentCourseEntity;
import com.example.model.vo.response.table.TeacherGradeItemVO;
import com.example.model.vo.response.table.TimetableItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentCourseDAO extends BaseDAO {

    public static final int PAGE_SIZE = 20;
    private final StudentCourseMapper mapper;

    public StudentCourseDAO(StudentCourseMapper mapper) {
        this.mapper = mapper;
    }


    public int save(StudentCourseEntity entity) {
        return mapper.insert(entity);
    }

    public int delete(Long id) {
        return mapper.deleteById(id);
    }

    public StudentCourseEntity get(Long id) {
        return mapper.selectById(id);
    }

    public int update(StudentCourseEntity entity) {
        return mapper.updateById(entity);
    }

    public int count(String className, String courseName, String studentName) {
        return mapper.count(className, courseName, studentName);
    }

    public List<StudentCourseItemVO> getPage(Integer index, String className, String courseName, String studentName) {
        Page<StudentCourseItemVO> page = new Page<>(index, PAGE_SIZE);

        return mapper.getPage(page, className, courseName, studentName).getRecords();
    }

    public int countByCourseId(Long courseId) {
        LambdaQueryWrapper<StudentCourseEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentCourseEntity::getCourseId, courseId);
        return Math.toIntExact(mapper.selectCount(wrapper));
    }

    public int countByStudentId(Long studentId) {
        LambdaQueryWrapper<StudentCourseEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentCourseEntity::getStudentId, studentId);
        return Math.toIntExact(mapper.selectCount(wrapper));
    }

    public StudentCourseEntity getByCourseIdAndStudentId(Long courseId, Long studentId) {
        LambdaQueryWrapper<StudentCourseEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(StudentCourseEntity::getId)
                .eq(StudentCourseEntity::getCourseId, courseId)
                .eq(StudentCourseEntity::getStudentId, studentId);
        return mapper.selectOne(wrapper);
    }

    public List<StudentCourseSelectedItemVO> listStudentCourseSelected(Long studentId) {
        return mapper.listStudentCourseSelected(studentId);
    }

    public List<StudentExamItemVO> listStudentExam(Long studentId) {
        return mapper.listStudentExam(studentId);
    }

    public Integer countStudentCourseSelectedByTimePart(Long studentId, String timePart) {
        return mapper.countStudentCourseSelectedByTimePart(studentId, timePart);
    }

    public List<TimetableItemVO> listStudentTimetable(Long studentId) {
        return mapper.listStudentTimetable(studentId);
    }

    public Integer countTeacherGrade(Long teacherId, String courseName, String studentName) {
        return mapper.countTeacherGrade(teacherId, courseName, studentName);
    }

    public List<TeacherGradeItemVO> getTeacherGradePage(
            Integer index,
            Long teacherId,
            String courseName,
            String studentName
    ) {
        Page<TeacherGradeItemVO> page = new Page<>(index, PAGE_SIZE);
        return mapper.getTeacherGradePage(page, teacherId, courseName, studentName).getRecords();
    }
}
