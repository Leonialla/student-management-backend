package com.example.controller.student;

import com.example.model.vo.request.StudentInfoFormVO;
import com.example.model.vo.response.ResultVO;
import com.example.config.permission.annotation.Student;
import com.example.controller.BaseController;
import com.example.service.student.InfoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Student
@RequestMapping("/student/info")
@RestController
public class InfoController extends BaseController {

    private final InfoService service;

    public InfoController(InfoService service) {
        this.service = service;
    }

    @GetMapping
    public ResultVO get() {
        return service.get();
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated StudentInfoFormVO formVO) {
        return service.update(formVO);
    }
}
