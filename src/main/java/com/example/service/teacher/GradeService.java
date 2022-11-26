package com.example.service.teacher;

import com.example.model.vo.TeacherGradeVO;
import com.example.model.vo.response.ResultVO;
import com.example.manager.OptionManager;
import com.example.manager.teacher.GradeManager;
import com.example.model.entity.StudentCourseEntity;
import com.example.service.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class GradeService extends BaseService {

    private final GradeManager manager;
    private final OptionManager optionManager;

    public GradeService(GradeManager manager, OptionManager optionManager) {
        this.manager = manager;
        this.optionManager = optionManager;
    }

    public ResultVO getPageCount(String courseName, String studentName) {
        Long teacherId = getUserId();
        return result(manager.getTeacherGradePageCount(teacherId, courseName, studentName));
    }

    public ResultVO getPage(Integer index, String courseName, String studentName) {
        Long teacherId = getUserId();
        return result(manager.getTeacherGradePage(index, teacherId, courseName, studentName));
    }

    public ResultVO update(TeacherGradeVO vo) {
        if (!optionManager.getAllowTeacherGrade()) {
            return failedResult("现在不是打分时间!");
        }

        Long teacherId = getUserId();
        StudentCourseEntity studentCourse = manager.getStudentCourseById(vo.getStudentCourseId());
        if (studentCourse == null) {
            return failedResult("学生选课 " + vo.getStudentCourseId() + "不存在");
        }
        if (!manager.getCourseById(studentCourse.getCourseId()).getTeacherId().equals(teacherId)) {
            return failedResult("此课程非您教授");
        }

        BeanUtils.copyProperties(vo, studentCourse);

        manager.updateStudentCourse(studentCourse);
        return result("打分成功");
    }

    public ResultVO get(Long studentCourseId) {
        if (!optionManager.getAllowTeacherGrade()) {
            return failedResult("现在不是打分时间!");
        }

        Long teacherId = getUserId();
        StudentCourseEntity studentCourse = manager.getStudentCourseById(studentCourseId);
        if (studentCourse == null) {
            return failedResult("学生选课 " + studentCourseId + "不存在");
        }
        if (!manager.getCourseById(studentCourse.getCourseId()).getTeacherId().equals(teacherId)) {
            return failedResult("此课程非您教授");
        }

        TeacherGradeVO vo = new TeacherGradeVO();
        BeanUtils.copyProperties(studentCourse, vo);
        vo.setStudentCourseId(studentCourseId);

        return result(vo);
    }
}
