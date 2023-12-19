package com.david.aw.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DayWeatherInfo {
    String type;
    String temperature;
    int fahrenheit;
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

    public boolean validateTemperature() {
        // validate the value with temperature in Celsius
        if (!temperature.contains("°")) {
            return false;
        }
        // Convert fahrenheit
        fahrenheit =
                ((Integer.parseInt(temperature.substring(0, temperature.indexOf("°"))) * 9) / 5)
                        + 32;
        return true;
    }
}
