package com.example.service.student;

import com.example.model.vo.response.ResultVO;
import com.example.manager.OptionManager;
import com.example.manager.student.CourseManager;
import com.example.model.entity.StudentCourseEntity;
import com.example.service.BaseService;
import org.springframework.stereotype.Service;

@Service("studentCourseServiceModular")
public class CourseService extends BaseService {
    private final CourseManager manager;
    private final OptionManager optionManager;

    public CourseService(CourseManager manager, OptionManager optionManager) {
        this.manager = manager;
        this.optionManager = optionManager;
    }

    public ResultVO list() {
        Long studentId = getUserId();
        return result(manager.listStudentCourseSelected(studentId));
    }

    public ResultVO delete(Long studentCourseId) {
        Long studentId = getUserId();
        if (!optionManager.getAllowStudentSelect()) {
            return failedResult("现在不是选课退课时间!");
        }
        StudentCourseEntity studentCourse = manager.getStudentCourseById(studentCourseId);
        if (studentCourse == null) {
            return failedResult("学生选课 " + studentCourseId + "不存在！");
        }
        if (!studentCourse.getStudentId().equals(studentId)) {
            return failedResult("此课程非此学生所选！");
        }
        if (studentCourse.getDailyScore() != null || studentCourse.getExamScore() != null || studentCourse.getScore() != null) {
            return failedResult("学生已获得成绩, 不能退选！");
        }

        manager.deleteStudentCourse(studentCourse);
        return result("退选成功！");
    }
}
