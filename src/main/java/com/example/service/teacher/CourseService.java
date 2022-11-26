package com.example.service.teacher;

import com.example.model.vo.response.ResultVO;
import com.example.util.LessonTimeConverter;
import com.example.manager.teacher.CourseManager;
import com.example.model.vo.response.table.TeacherCourseItemVO;
import com.example.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("teacherCourseService")
public class CourseService extends BaseService {

    private final CourseManager manager;
    private final LessonTimeConverter lessonTimeConverter;

    public CourseService(CourseManager manager, LessonTimeConverter lessonTimeConverter) {
        this.manager = manager;
        this.lessonTimeConverter = lessonTimeConverter;
    }

    public ResultVO list() {
        Long teacherId = getUserId();

        List<TeacherCourseItemVO> list = manager.listTeacherCourse(teacherId);
        for (TeacherCourseItemVO vo : list) {
            vo.setTime(lessonTimeConverter.covertTimePart(vo.getTime()));
        }

        return result(list);
    }
}
