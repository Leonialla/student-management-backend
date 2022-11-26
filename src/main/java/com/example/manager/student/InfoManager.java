package com.example.manager.student;

import com.example.dao.StudentDAO;
import com.example.model.vo.response.StudentInfoVO;
import com.example.manager.BaseManager;
import com.example.model.entity.StudentEntity;
import org.springframework.stereotype.Component;

@Component
public class InfoManager extends BaseManager {

    private final StudentDAO studentDAO;

    public InfoManager(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public StudentInfoVO getStudentInfoByStudentId(Long studentId) {
        return studentDAO.getStudentInfoById(studentId);
    }

    public StudentEntity getStudentById(Long studentId) {
        return studentDAO.get(studentId);
    }

    public void updateStudent(StudentEntity entity) {
        studentDAO.update(entity);
    }
}
