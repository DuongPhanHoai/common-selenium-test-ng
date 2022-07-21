package com.david.test.core.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServerInfo {
    @Builder.Default String baseURL = "http://localhost:8080/";

    @Builder.Default String loginEndPoint = "sg/oauth/access_token";

    @Builder.Default String loginEmail = "admin@example.com";

    @Builder.Default String loginPwd = "password";

    @Builder.Default String apiBaseURL = "https://v4.ninja.com/";

    @Builder.Default String clientId = "f288852c-7a4a-4d5e-8267-a83778233ad0";

    @Builder.Default String clientSecret = "be2z8YU6ubykr1oJYWee5Q";
}
