package com.sauce.test.data;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServerInfo {
    @Builder.Default String baseURL = "https://www.saucedemo.com/";
    @Builder.Default String standardUsername = "standard_user";
    @Builder.Default String standardUserPwd = "c2VjcmV0X3NhdWNl";

    // Decode Pwd
    public String getStandardUserPwd() {
        try {
            // Decode Base64
            byte[] result = Base64.getDecoder().decode(standardUserPwd);
            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception e) {e.printStackTrace();}
        return standardUserPwd;
    }
}
