package com.example.service;

import com.example.model.bo.AuthInfoBO;
import com.example.model.bo.LoginStatusBO;
import com.example.model.vo.response.ResultVO;
import com.example.manager.LoginStatusManager;
import com.example.manager.UserManager;
import com.example.util.Md5Encrypt;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import static com.example.model.constant.UserType.*;

@Service
public class UserService extends BaseService {

    private static final String PASSWORD_SALT = "student_management_system@888";

    private final HttpSession session;
    private final UserManager manager;
    private final LoginStatusManager loginStatusManager;
    private final Md5Encrypt md5Encrypt;

    public UserService(
            HttpSession session,
            UserManager manager,
            LoginStatusManager loginStatusManager,
            Md5Encrypt md5Encrypt
    ) {
        this.session = session;
        this.manager = manager;
        this.loginStatusManager = loginStatusManager;
        this.md5Encrypt = md5Encrypt;
    }

    public ResultVO login(String username, String password, Integer userType) {
        AuthInfoBO authInfo = manager.getAuthInfoByUsername(username, userType);
        if (authInfo == null) {
            return failedResult("用户不存在");
        }
        if (!computePasswordHash(password).equals(authInfo.getPassword())) {
            return failedResult("密码错误");
        }
        if (authInfo.getUserType().equals(STUDENT)) {
            manager.updateStudentLastLoginTime(username);
        }

        LoginStatusBO statusBO = LoginStatusBO.fromAuthInfo(authInfo);
        loginStatusManager.setLoginStatus(session, statusBO);

        return result(statusBO);
    }

    public ResultVO getLoginStatus() {
        LoginStatusBO statusBO = loginStatusManager.getLoginStatus(session);
        return result(statusBO);
    }

    public ResultVO logout() {
        loginStatusManager.setLoginStatus(session, null);
        return result("注销成功");
    }

    public String computePasswordHash(String password) {
        return md5Encrypt.computeHexString(md5Encrypt.computeHexString(password) + PASSWORD_SALT);
    }
}
