package com.example.controller.admin;

import com.example.model.vo.response.ResultVO;
import com.example.config.permission.annotation.Admin;
import com.example.controller.BaseController;
import com.example.model.entity.StudentCourseEntity;
import com.example.service.admin.StudentCourseService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static com.example.config.permission.annotation.Admin.STUDENT_COURSE_MANAGE;

@Admin(STUDENT_COURSE_MANAGE)
@RequestMapping("/admin/student/course")
@RestController
public class StudentCourseController extends BaseController {

    private final StudentCourseService service;

    public StudentCourseController(StudentCourseService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public ResultVO create(@RequestBody @Validated StudentCourseEntity entity) {
        return service.create(entity);
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated StudentCourseEntity entity) {
        return service.update(entity);
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String className, String courseName, String studentName) {
        return service.getPageCount(className, courseName, studentName);
    }

    @RequestMapping("/page")
    public ResultVO getPage(String className, String courseName, String studentName) {
        return service.getPage(1, className, courseName, studentName);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String className, String courseName, String studentName) {
        return service.getPage(index, className, courseName, studentName);
    }
}
