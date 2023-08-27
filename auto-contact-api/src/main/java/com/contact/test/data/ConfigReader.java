package com.contact.test.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import org.slf4j.LoggerFactory;

import com.david.test.core.util.LogUtils;

public class ConfigReader {
    protected static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ConfigReader.class);
    static ApiConfInfo apiConfInfo = null;

    public static ApiConfInfo getServerInfo(String env) {
        if (Objects.isNull(apiConfInfo)) {
            // Try to read
            Properties properties = new Properties();
            String configPropFilePath = String.format("/%s/config.properties", env);
            try (InputStream resourceStream =
                    ConfigReader.class.getResourceAsStream(configPropFilePath)) {
                if (Objects.nonNull(resourceStream)) {
                    properties.load(resourceStream);
                    String baseURL = properties.getProperty("baseURL");
                    String email = properties.getProperty("email");
                    String pwd = properties.getProperty("pwd");
                    apiConfInfo =
                            ApiConfInfo.builder().baseURL(baseURL).email(email).pwd(pwd).build();
                    return apiConfInfo;
                }
            } catch (IOException e) {
                LOG.error(LogUtils.getFullStack(e));
            }
            // Cannot read
            apiConfInfo = ApiConfInfo.builder().build();
        }
        return apiConfInfo;
    }
}
