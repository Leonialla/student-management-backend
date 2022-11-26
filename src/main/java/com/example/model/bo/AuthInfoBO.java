package com.example.model.bo;

import com.example.model.entity.AdminEntity;
import com.example.model.entity.StudentEntity;
import com.example.model.entity.TeacherEntity;
import lombok.Data;

import static com.example.model.constant.UserType.ADMIN;
import static com.example.model.constant.UserType.STUDENT;
import static com.example.model.constant.UserType.TEACHER;

@Data
public class AuthInfoBO {

    private Long id;
    private String username;
    private String password;
    private Integer userType;
    private Integer permission;

    private AuthInfoBO(Long id, String username, String password, Integer userType) {
        this(id, username, password, userType, 0);
    }

    private AuthInfoBO(Long id, String username, String password, Integer userType, Integer permission) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.permission = permission;
    }

    public static AuthInfoBO fromStudent(StudentEntity entity) {
        if (entity == null) {
            return null;
        }
        return new AuthInfoBO(entity.getId(), entity.getNumber(), entity.getPassword(), STUDENT);
    }

    public static AuthInfoBO fromTeacher(TeacherEntity entity) {
        if (entity == null) {
            return null;
        }
        return new AuthInfoBO(entity.getId(), entity.getNumber(), entity.getPassword(), TEACHER);
    }

    public static AuthInfoBO fromAdmin(AdminEntity entity) {
        if (entity == null) {
            return null;
        }
        return new AuthInfoBO(entity.getId(), entity.getUsername(), entity.getPassword(), ADMIN, entity.getPrivilege());
    }
}
