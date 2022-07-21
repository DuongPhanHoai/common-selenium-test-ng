package com.david.awb.test.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import org.slf4j.LoggerFactory;

import com.david.test.core.dto.ServerInfo;
import com.david.test.core.util.LogUtils;

public class ConfigReader {
    protected static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ConfigReader.class);
    static ConfigReader configReader = new ConfigReader();
    static ServerInfo serverInfo;

    public static ServerInfo getServerInfo() {
        if (Objects.isNull(serverInfo)) {
            Properties properties = new Properties();
            try (InputStream resourceStream =
                    configReader.getClass().getResourceAsStream("/config.properties")) {
                properties.load(resourceStream);
            } catch (IOException e) {
                LOG.error(LogUtils.getFullStack(e));
            }
            String baseURL = properties.getProperty("baseURL");
            String loginEndPoint = properties.getProperty("loginEndPoint");
            String loginEmail = properties.getProperty("loginEmail");
            String loginPwd = properties.getProperty("loginPwd");

            String apiBaseURL = properties.getProperty("apiBaseURL");
            String clientId = properties.getProperty("clientId");
            String clientSecret = properties.getProperty("clientSecret");

            serverInfo =
                    ServerInfo.builder()
                            .baseURL(baseURL)
                            .loginEndPoint(loginEndPoint)
                            .loginEmail(loginEmail)
                            .loginPwd(loginPwd)
                            .apiBaseURL(apiBaseURL)
                            .clientId(clientId)
                            .clientSecret(clientSecret)
                            .build();
        }
        return serverInfo;
    }
}
