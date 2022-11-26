package com.example.service;

import com.example.model.bo.LoginStatusBO;
import com.example.model.vo.response.ResultVO;
import com.example.manager.LoginStatusManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

import static com.example.model.vo.response.ResultVO.FAIL;
import static com.example.model.vo.response.ResultVO.SUCCESS;

public class BaseService {

    @Autowired
    private HttpSession session;
    @Autowired
    private LoginStatusManager loginStatusManager;

    private LoginStatusBO getLoginStatus() {
        return loginStatusManager.getLoginStatus(session);
    }

    protected Long getUserId() {
        return getLoginStatus().getUserId();
    }

    protected ResultVO result(Object data) {
        return new ResultVO(SUCCESS, "success", data);
    }

    protected ResultVO result(Object data, String message) {
        return new ResultVO(SUCCESS, message, data);
    }

    protected ResultVO failedResult(String message) {
        return new ResultVO(FAIL, message, null);
    }

    protected ResultVO failedResult(String message, Object data) {
        return new ResultVO(FAIL, message, data);
    }
}
