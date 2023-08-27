package com.contact.test.data;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiConfInfo {
    @Builder.Default String baseURL = "https://www.saucedemo.com/";
    @Builder.Default String email = "standard_user";
    @Builder.Default String pwd = "c2VjcmV0X3NhdWNl";

    // Decode Pwd
    public String getPwd() {
        try {
            // Decode Base64
            byte[] result = Base64.getDecoder().decode(pwd);
            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pwd;
    }
}
