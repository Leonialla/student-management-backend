package com.example.controller.teacher;

import com.example.model.vo.TeacherGradeVO;
import com.example.model.vo.response.ResultVO;
import com.example.config.permission.annotation.Teacher;
import com.example.service.teacher.GradeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Teacher
@RequestMapping("/teacher/grade")
@RestController
public class GradeController {

    private final GradeService service;

    public GradeController(GradeService service) {
        this.service = service;
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String courseName, String studentName) {
        return service.getPageCount(courseName, studentName);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String courseName, String studentName) {
        return service.getPage(index, courseName, studentName);
    }

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated TeacherGradeVO vo) {
        return service.update(vo);
    }
}
