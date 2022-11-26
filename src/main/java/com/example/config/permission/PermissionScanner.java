package com.example.config.permission;

import com.example.config.permission.annotation.Admin;
import com.example.config.permission.annotation.Login;
import com.example.config.permission.annotation.NoLimit;
import com.example.config.permission.annotation.Student;
import com.example.config.permission.annotation.Teacher;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static com.example.model.constant.UserType.ADMIN;
import static com.example.model.constant.UserType.NO;
import static com.example.model.constant.UserType.STUDENT;
import static com.example.model.constant.UserType.TEACHER;

@Component
public class PermissionScanner {

    public Permission scan(Method method) {
        Admin annotation;
        if (getAnnotation(method, NoLimit.class) != null) {
            return new Permission(false);

        } else if (getAnnotation(method, Login.class) != null) {
            return new Permission(NO, 0);

        } else if (getAnnotation(method, Student.class) != null) {
            return new Permission(STUDENT);

        } else if (getAnnotation(method, Teacher.class) != null) {
            return new Permission(TEACHER);

        } else if ((annotation = getAnnotation(method, Admin.class)) != null) {
            return new Permission(ADMIN, annotation.value());
        }

        return new Permission(false);
    }

    private <T extends Annotation> T getAnnotation(Method method, Class<T> annotationClass) {
        T annotation = method.getDeclaredAnnotation(annotationClass);
        if (annotation == null) {
            annotation = method.getDeclaringClass().getDeclaredAnnotation(annotationClass);
        }

        return annotation;
    }
}
