package com.example.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.model.vo.response.StudentInfoVO;
import com.example.model.entity.StudentEntity;
import com.example.model.vo.response.table.StudentItemVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper extends BaseMapper<StudentEntity> {

    Long getDepartmentIdById(Long studentId);

    Integer getGradeById(Long studentId);

    Integer count(
            @Param("majorName") String majorName,
            @Param("className") String className,
            @Param("name") String name
    );

    IPage<StudentItemVO> getPage(
            IPage<StudentItemVO> page,
            @Param("majorName") String majorName,
            @Param("className") String className,
            @Param("name") String name
    );

    StudentInfoVO getStudentInfoById(Long studentId);
}
