package com.example.service.student;

import com.example.model.vo.response.ResultVO;
import com.example.manager.student.TimetableManager;
import com.example.service.BaseService;
import org.springframework.stereotype.Service;

@Service("studentTimetableService")
public class TimetableService extends BaseService {

    private final TimetableManager manager;

    public TimetableService(TimetableManager manager) {
        this.manager = manager;
    }

    public ResultVO get() {
        Long studentId = getUserId();
        return result(manager.listStudentTimetable(studentId));
    }
}
