package com.example.service.admin;

import com.example.model.vo.response.ResultVO;
import com.example.manager.admin.StudentManager;
import com.example.model.entity.StudentEntity;
import com.example.service.BaseService;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends BaseService {
    private final StudentManager manager;
    private final UserService userService;

    public StudentService(StudentManager manager, UserService userService) {
        this.manager = manager;
        this.userService = userService;
    }

    public ResultVO getPageCount(String majorName, String className, String name) {
        return result(manager.getPageCount(majorName, className, name));
    }

    public ResultVO getPage(Integer index, String majorName, String className, String name) {
        return result(manager.getPage(index, majorName, className, name));
    }

    public ResultVO get(Long id) {
        StudentEntity entity = manager.get(id);
        if (entity == null) {
            return failedResult("学生 " + id + "不存在!");
        }

        entity.setPassword("");

        return result(entity);
    }

    public ResultVO update(StudentEntity entity) {
        StudentEntity origin = manager.get(entity.getId());
        if (origin == null) {
            return failedResult("学生 " + entity.getId() + "不存在!");
        }
        if (manager.getClassById(entity.getClassId()) == null) {
            return failedResult("所属班级 " + entity.getClassId() + "不存在!");
        }

        if (entity.getPassword().equals("")) {
            entity.setPassword(origin.getPassword());
        } else {
            entity.setPassword(userService.computePasswordHash(entity.getPassword()));
        }

        manager.update(entity);
        return result("更新成功");
    }

    public ResultVO delete(Long id) {
        if (manager.get(id) == null) {
            return failedResult("学生 " + id + "不存在!");
        }
        if (manager.hasStudentCourse(id)) {
            return failedResult("此学生还有未退选课程");
        }

        manager.delete(id);
        return result("删除成功");
    }

    public ResultVO create(StudentEntity entity) {
        if (manager.getByNumber(entity.getNumber()) != null) {
            return failedResult("学生 " + entity.getName() + "已存在！");
        }
        if (manager.getClassById(entity.getClassId()) == null) {
            return failedResult("所属班级 " + entity.getClassId() + "不存在!");
        }

        entity.setPassword(userService.computePasswordHash(entity.getPassword()));
        manager.create(entity);
        return result("添加成功");
    }

    public ResultVO listName() {
        return result(manager.listName());
    }
}
