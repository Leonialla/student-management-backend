package com.example.manager;

import com.example.dao.AdminDAO;
import com.example.dao.StudentDAO;
import com.example.dao.TeacherDAO;
import com.example.model.bo.AuthInfoBO;
import com.example.model.entity.StudentEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.example.model.constant.UserType.ADMIN;
import static com.example.model.constant.UserType.STUDENT;
import static com.example.model.constant.UserType.TEACHER;

@Component
public class UserManager extends BaseManager {

    private final AdminDAO adminDAO;
    private final TeacherDAO teacherDAO;
    private final StudentDAO studentDAO;

    public UserManager(AdminDAO adminDAO, TeacherDAO teacherDAO, StudentDAO studentDAO) {
        this.adminDAO = adminDAO;
        this.teacherDAO = teacherDAO;
        this.studentDAO = studentDAO;
    }

    public AuthInfoBO getAuthInfoByUsername(String username, Integer userType) {
        if (userType == STUDENT) {
            return AuthInfoBO.fromStudent(studentDAO.getByNumber(username));
        } else if (userType == TEACHER) {
            return AuthInfoBO.fromTeacher(teacherDAO.getByNumber(username));
        } else if (userType == ADMIN) {
            return AuthInfoBO.fromAdmin(adminDAO.getByUsername(username));
        }
        return null;
    }

    public void updateStudentLastLoginTime(String number) {
        StudentEntity entity = studentDAO.getByNumber(number);
        if (entity == null) {
            return;
        }

        entity.setLastLoginTime(new Date());
        studentDAO.update(entity);
    }
}
