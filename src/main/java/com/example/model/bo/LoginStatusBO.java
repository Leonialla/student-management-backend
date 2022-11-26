package com.example.model.bo;

import lombok.Data;

import java.io.Serializable;

import static com.example.model.constant.UserType.NO;

@Data
public class LoginStatusBO implements Serializable {

    private Boolean loggedIn = false;
    private Long userId;
    private String username;
    private Integer userType = NO;
    private Integer permission = 0;

    public static LoginStatusBO fromAuthInfo(AuthInfoBO authInfo) {
        LoginStatusBO loginStatus = new LoginStatusBO();
        loginStatus.loggedIn = true;
        loginStatus.userId = authInfo.getId();
        loginStatus.username = authInfo.getUsername();
        loginStatus.userType = authInfo.getUserType();
        loginStatus.permission = authInfo.getPermission();

        return loginStatus;
    }
}
