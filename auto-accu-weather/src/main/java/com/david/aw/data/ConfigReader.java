package com.david.aw.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import org.slf4j.LoggerFactory;

import com.david.test.core.util.LogUtils;

public class ConfigReader {
    protected static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ConfigReader.class);
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
                LOG.error(LogUtils.getFullStack(e));
            }
            // Cannot read
            serverInfo = ServerInfo.builder().build();
        }
        return serverInfo;
    }
}
