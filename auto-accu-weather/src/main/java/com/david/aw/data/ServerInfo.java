package com.david.aw.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServerInfo {
    @Builder.Default String baseURL = "https://www.accuweather.com/";
}
