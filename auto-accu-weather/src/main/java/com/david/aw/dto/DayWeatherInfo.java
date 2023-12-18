package com.david.aw.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DayWeatherInfo {
    String type;
    String temperature;
    String weatherStatus;
    String dayRealFeel;
    String humidity;

    public static String convertDataToStatus(String dataSource) {
        if (dataSource.contains("13.svg")) {
            return "Sun-Cloudy-Rain";
        } else if (dataSource.contains("35.svg")) {
            return "Clear-Cloudy";
        }
        return dataSource;
    }
}
