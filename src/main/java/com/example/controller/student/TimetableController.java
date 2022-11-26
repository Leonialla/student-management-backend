package com.example.controller.student;

import com.example.model.vo.response.ResultVO;
import com.example.config.permission.annotation.Student;
import com.example.controller.BaseController;
import com.example.service.student.TimetableService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Student
@RequestMapping("/student/timetable")
@RestController("student_timeTableController")
public class TimetableController extends BaseController {

    private final TimetableService service;

    public TimetableController(TimetableService service) {
        this.service = service;
    }

    @RequestMapping
    public ResultVO get() {
        return service.get();
    }
}
