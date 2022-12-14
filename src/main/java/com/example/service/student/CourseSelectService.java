package com.example.service.student;

import com.example.model.bo.StudentCourseSelectItemBO;
import com.example.model.vo.response.ResultVO;
import com.example.model.vo.response.table.StudentCourseSelectItemVO;
import com.example.util.LessonTimeConverter;
import com.example.manager.OptionManager;
import com.example.manager.student.CourseSelectManager;
import com.example.model.entity.CourseEntity;
import com.example.model.entity.StudentCourseEntity;
import com.example.model.entity.StudentEntity;
import com.example.service.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseSelectService extends BaseService {

    private final CourseSelectManager manager;
    private final OptionManager optionManager;
    private final LessonTimeConverter lessonTimeConverter;

    public CourseSelectService(CourseSelectManager manager, OptionManager optionManager, LessonTimeConverter lessonTimeConverter) {
        this.manager = manager;
        this.optionManager = optionManager;
        this.lessonTimeConverter = lessonTimeConverter;
    }

    public ResultVO getPageCount(String courseName, String teacherName) {
        Long studentId = getUserId();
        return result(manager.getPageCount(studentId, courseName, teacherName));
    }

    public ResultVO getPage(Integer index, String courseName, String teacherName) {
        Long studentId = getUserId();

        List<StudentCourseSelectItemBO> boList = manager.getPage(index, studentId, courseName, teacherName);
        List<StudentCourseSelectItemVO> voList = new ArrayList<>(boList.size());

        for (StudentCourseSelectItemBO bo : boList) {
            StudentCourseSelectItemVO vo = new StudentCourseSelectItemVO();
            BeanUtils.copyProperties(bo, vo);
            vo.setTime(lessonTimeConverter.covertTimePart(bo.getTime()));
            voList.add(vo);
        }

        return result(voList);
    }

    public ResultVO create(Long courseId) {
        Long studentId = getUserId();

        if (!optionManager.getAllowStudentSelect()) {
            return failedResult("????????????????????????!");
        }
        StudentEntity student = manager.getStudentById(studentId);
        CourseEntity course = manager.getCourseById(courseId);
        if (student == null) {
            return failedResult("?????? " + studentId + "?????????!");
        }
        if (course == null) {
            return failedResult("?????? " + courseId + "?????????!");
        }
        if (!manager.inSameDepartment(courseId, studentId)) {
            return failedResult("???????????????????????????????????????!");
        }
        if (course.getSelectedCount() >= course.getMaxSize()) {
            return failedResult("???????????????!");
        }
        if (manager.getStudentCourseByCourseIdAndStudentId(courseId, studentId) != null) {
            return failedResult("????????????????????????!");
        }
        if (!manager.getStudentGradeById(student.getId()).equals(course.getGrade())) {
            return failedResult("????????????????????????????????????");
        }
        String timePart = splitTimePart(course.getTime());
        if (manager.countStudentCourseSelectedByTimePart(studentId, timePart) > 0) {
            return failedResult("??????????????????!");
        }

        StudentCourseEntity studentCourse = new StudentCourseEntity();
        studentCourse.setCourseId(courseId);
        studentCourse.setStudentId(studentId);
        manager.create(studentCourse);

        return result("????????????!");
    }

    private String splitTimePart(String time) {
        String[] spilt = time.split("-");
        return spilt[0] + "-" + spilt[1];
    }
}
