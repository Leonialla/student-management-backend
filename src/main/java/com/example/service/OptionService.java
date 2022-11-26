package com.example.service;

import com.example.model.vo.response.ResultVO;
import com.example.manager.OptionManager;
import org.springframework.stereotype.Service;

@Service
public class OptionService extends BaseService {

    private final OptionManager manager;

    public OptionService(OptionManager manager) {
        this.manager = manager;
    }

    public ResultVO setAllowStudentSelect(Boolean status) {
        manager.setAllowStudentSelect(status);
        return result("成功");
    }

    public ResultVO getAllowStudentSelect() {
        return result(manager.getAllowStudentSelect());
    }

    public ResultVO setAllowTeacherGrade(Boolean status) {
        manager.setAllowTeacherGrade(status);
        return result("成功");
    }

    public ResultVO getAllowTeacherGrade() {
        return result(manager.getAllowTeacherGrade());
    }
}
