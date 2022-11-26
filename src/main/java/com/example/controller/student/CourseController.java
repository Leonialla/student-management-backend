package com.example.controller.student;

import com.example.model.vo.response.ResultVO;
import com.example.config.permission.annotation.Student;
import com.example.controller.BaseController;
import com.example.service.student.CourseService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Student
@RequestMapping("/student/course")
@RestController("student_courseController")
public class CourseController extends BaseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @RequestMapping("/list")
    public ResultVO list() {
        return service.list();
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
