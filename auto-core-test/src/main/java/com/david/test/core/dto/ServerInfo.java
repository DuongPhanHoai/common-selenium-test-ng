package com.david.test.core.dto;

public class ServerInfo {
    String baseURL = "http://localhost:8080/";

    public String getBaseURL() {
        return baseURL;
    }

    String loginEndPoint = "api/auth/signin";

    public String getLoginEndPoint() {
        return loginEndPoint;
    }

    String loginEmail = "admin@example.com";

    public String getLoginEmail() {
        return loginEmail;
    }

    String loginPwd = "password";

    public String getLoginPwd() {
        return loginPwd;
    }

    public ServerInfo(String baseURL, String loginEndPoint, String loginEmail, String loginPwd) {
        this.baseURL = baseURL;
        this.loginEndPoint = loginEndPoint;
        this.loginEmail = loginEmail;
        this.loginPwd = loginPwd;
    }
}
