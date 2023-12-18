package com.david.aw.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import com.david.test.core.util.LogUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfigReader {
    static ServerInfo serverInfo = null;

    public static ServerInfo getServerInfo(String env) {
        if (Objects.isNull(serverInfo)) {
            // Try to read
            Properties properties = new Properties();
            String configPropFilePath = String.format("/%s/config.properties", env);
            try (InputStream resourceStream =
                    ConfigReader.class.getResourceAsStream(configPropFilePath)) {
                if (Objects.nonNull(resourceStream)) {
                    properties.load(resourceStream);
                    String baseURL = properties.getProperty("baseURL");
                    serverInfo = ServerInfo.builder().baseURL(baseURL).build();
                    return serverInfo;
                }
            } catch (IOException e) {
                log.error(LogUtils.getFullStack(e));
            }
            // Cannot read
            serverInfo = ServerInfo.builder().build();
        }
        return serverInfo;
    }
}
