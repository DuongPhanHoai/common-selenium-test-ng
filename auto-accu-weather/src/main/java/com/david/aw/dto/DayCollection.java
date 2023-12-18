package com.david.aw.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DayCollection {
    @Builder.Default Long getTime = java.lang.System.currentTimeMillis();
    List<DayInfo> dayInfos;
}
