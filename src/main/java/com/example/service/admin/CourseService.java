package com.example.service.admin;

import com.example.model.bo.CourseItemBO;
import com.example.model.vo.response.ResultVO;
import com.example.util.LessonTimeConverter;
import com.example.manager.admin.CourseManager;
import com.example.model.entity.CourseEntity;
import com.example.model.vo.response.table.CourseItemVO;
import com.example.service.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService extends BaseService {

    private final CourseManager manager;
    private final LessonTimeConverter lessonTimeConverter;

    public CourseService(CourseManager manager, LessonTimeConverter lessonTimeConverter) {
        this.manager = manager;
        this.lessonTimeConverter = lessonTimeConverter;
    }

    public ResultVO getPageCount(String departmentName, String teacherName, String name) {
        return result(manager.getPageCount(departmentName, teacherName, name));
    }

    public ResultVO getPage(Integer index, String departmentName, String teacherName, String name) {
        List<CourseItemBO> boList = manager.getPage(index, departmentName, teacherName, name);
        List<CourseItemVO> voList = new ArrayList<>(boList.size());

        for (CourseItemBO bo : boList) {
            CourseItemVO vo = new CourseItemVO();
            BeanUtils.copyProperties(bo, vo);
            vo.setTime(lessonTimeConverter.covertTimePart(bo.getTime()));
            voList.add(vo);
        }

        return result(voList);
    }

    public ResultVO get(Long id) {
        CourseEntity entity = manager.get(id);
        if (entity == null) {
            return failedResult("课程 " + id + "不存在!");
        }

        return result(entity);
    }

    public ResultVO update(CourseEntity entity) {
        CourseEntity origin = manager.get(entity.getId());
        if (origin == null) {
            return failedResult("课程 " + entity.getId() + "不存在!");
        }
        if (manager.getTeacherById(entity.getTeacherId()) == null) {
            return failedResult("授课教师 " + entity.getTeacherId() + "不存在!");
        }

        entity.setSelectedCount(origin.getSelectedCount());

        manager.update(entity);
        return result("更新成功");
    }

    public ResultVO delete(Long id) {
        if (manager.get(id) == null) {
            return failedResult("课程 " + id + "不存在!");
        }
        if (manager.hasStudentCourse(id)) {
            return failedResult("还有学生未退选此课程");
        }

        manager.delete(id);
        return result("删除成功");
    }

    public ResultVO create(CourseEntity entity) {
        if (manager.getByName(entity.getName()) != null) {
            return failedResult("课程 " + entity.getName() + "已存在！");
        }
        if (manager.getTeacherById(entity.getTeacherId()) == null) {
            return failedResult("授课教师 " + entity.getTeacherId() + "不存在!");
        }

        manager.create(entity);
        return result("添加成功");
    }

    public ResultVO listName() {
        return result(manager.listName());
    }
}
