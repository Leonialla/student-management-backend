package com.example.service.admin;

import com.example.model.vo.response.ResultVO;
import com.example.manager.admin.ClassManager;
import com.example.model.entity.ClassEntity;
import com.example.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ClassService extends BaseService {

    private final ClassManager manager;

    public ClassService(ClassManager manager) {
        this.manager = manager;
    }

    public ResultVO getPageCount(String departmentName, String majorName, String name) {
        return result(manager.getPageCount(departmentName, majorName, name));
    }

    public ResultVO getPage(Integer index, String departmentName, String majorName, String name) {
        return result(manager.getPage(index, departmentName, majorName, name));
    }

    public ResultVO get(Long id) {
        ClassEntity entity = manager.get(id);
        if (entity == null) {
            return failedResult("班级: " + id + "不存在!");
        }

        return result(entity);
    }

    public ResultVO update(ClassEntity entity) {
        if (manager.get(entity.getId()) == null) {
            return failedResult("班级 " + entity.getId() + "不存在!");
        }
        if (manager.getMajorById(entity.getMajorId()) == null) {
            return failedResult("所属专业 " + entity.getMajorId() + "不存在!");
        }

        manager.update(entity);
        return result("更新成功");
    }

    public ResultVO delete(Long id) {
        if (manager.get(id) == null) {
            return failedResult("班级 " + id + "不存在!");
        }
        if (manager.hasStudent(id)) {
            return failedResult("此班级中还有学生未被删除");
        }

        manager.delete(id);
        return result("删除成功");
    }

    public ResultVO create(ClassEntity entity) {
        ClassEntity foundClass = manager.getByName(entity.getName());
        if (foundClass != null) {
            return failedResult("班级 " + foundClass.getId() + "已存在!");
        }
        if (manager.getMajorById(entity.getMajorId()) == null) {
            return failedResult("所属专业 " + entity.getMajorId() + "不存在!");
        }

        manager.create(entity);
        return result("添加成功");
    }

    public ResultVO listName() {
        return result(manager.listName());
    }
}
