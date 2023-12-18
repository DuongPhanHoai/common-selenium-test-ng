package com.david.aw.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DayInfo {
    String day;
    List<DayWeatherInfo> dayWeatherInfos;
}
