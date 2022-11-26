package com.example.service.teacher;

import com.example.model.vo.response.ResultVO;
import com.example.manager.teacher.TimetableManager;
import com.example.service.BaseService;
import org.springframework.stereotype.Service;

@Service("teacherTimetableService")
public class TimetableService extends BaseService {

    private final TimetableManager manager;

    public TimetableService(TimetableManager manager) {
        this.manager = manager;
    }

    public ResultVO get() {
        Long teacherId = getUserId();
        return result(manager.listTeacherTimetable(teacherId));
    }
}
