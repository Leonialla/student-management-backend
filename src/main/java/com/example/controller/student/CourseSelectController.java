package com.example.controller.student;

import com.example.model.vo.response.ResultVO;
import com.example.service.student.CourseSelectService;
import com.example.config.permission.annotation.Student;
import com.example.controller.BaseController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Student
@RequestMapping("/student/course/select")
@RestController
public class CourseSelectController extends BaseController {

    private final CourseSelectService service;

    public CourseSelectController(CourseSelectService service) {
        this.service = service;
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String courseName, String teacherName) {
        return service.getPageCount(courseName, teacherName);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String courseName, String teacherName) {
        return service.getPage(index, courseName, teacherName);
    }

    @PostMapping("/{id}")
    public ResultVO create(@PathVariable Long id) {
        return service.create(id);
    }
}
