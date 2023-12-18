package com.david.aw.test.web;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.david.aw.dto.DayCollection;
import com.david.aw.dto.DayInfo;
import com.david.aw.dto.DayWeatherInfo;
import com.david.aw.page.DayDetailPage;
import com.david.aw.page.DayPage;
import com.david.aw.page.HomePage;
import com.david.aw.test.BaseAWTest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class DayTests extends BaseAWTest {
    protected final Logger log = LoggerFactory.getLogger(DayTests.class);
    static final String SCAN_ALL_DAYS_JSON = "scanAllDays.json";
    private static final Type REVIEW_TYPE = new TypeToken<List<DayCollection>>() {}.getType();

    @Test
    public void scanAllDays() throws IOException {
        // Declare Pages
        HomePage homePage = new HomePage(getWebDriverManager());
        DayPage dayPage = new DayPage(getWebDriverManager());
        DayDetailPage dayDetailPage = new DayDetailPage(getWebDriverManager());

        // Load old data from file
        List<DayCollection> dayCollections = null;
        File file = new File(SCAN_ALL_DAYS_JSON);
        Gson gson = new Gson();
        if (file.exists() && !file.isDirectory()) {
            JsonReader reader = new JsonReader(new FileReader(file));
            dayCollections = gson.fromJson(reader, REVIEW_TYPE);
            reader.close();
        }
        if (Objects.isNull(dayCollections)) {
            dayCollections = new ArrayList<>();
        }

        // Weather tools Use case:
        // 1. Use https://www.accuweather.com
        homePage.launchHome(serverInfo);

        // 2. Select Daily menu -> Page will display weather information for 30 days
        homePage.openDayMenu();
        // + Example: At the top of page say: November 8 - December 22. And there are summary of
        // weather information of each day
        String title = dayPage.getTitle();

        // 3. For each day, retrieve following information
        // Day value:
        // + Example: Thursday, November 8
        List<DayInfo> dayInfos = dayPage.getAllDayInfos();

        // 3.1 Load the first day
        dayPage.clickFirstDayDetail();
        dayInfos.get(0).setDayWeatherInfos(dayDetailPage.getDayInfo());
        // 3.1 Loading for each day
        for (int i = 1; i < dayInfos.size() && i < 1; i++) {
            DayInfo dayInfo = dayInfos.get(i);
            log.info("scanning {}", dayInfo.getDay());
            Assert.assertEquals(dayInfo.getDay(), dayDetailPage.clickNextDay());
            // get Day (Morning or Afternoon)
            dayInfo.setDayWeatherInfos(dayDetailPage.getDayInfo());
        }

        // 4. Retrieve temperature in Fahrenheit and validate the value with temperature in Celsius.
        // Note: depend on location the temperature can be Fahrenheit of Celsius
        for (DayInfo dayInfo : dayInfos) {
            if (Objects.nonNull(dayInfo.getDayWeatherInfos())
                    && dayInfo.getDayWeatherInfos().size() > 0)
                for (DayWeatherInfo dayWeatherInfo : dayInfo.getDayWeatherInfos()) {
                    Assert.assertTrue(
                            dayWeatherInfo.validateTemperature(),
                            "Retrieve temperature in Fahrenheit and validate the value with temperature in Celsius.");
                }
        }
        // 5. The test should be supported to execute at least every 1 hour in a day.
        // Note this should use Jenkins CI/CD ot Task schedule from Windows/Linux/MacOS

        // 6. To save in a file all information retrieved.
        dayCollections.add(DayCollection.builder().dayInfos(dayInfos).build());

        try (Writer writer =
                Files.newBufferedWriter(Paths.get(SCAN_ALL_DAYS_JSON), StandardCharsets.UTF_8)) {
            Gson gwrite = new GsonBuilder().setPrettyPrinting().create();
            String temp = gwrite.toJson(dayCollections, ArrayList.class);
            gwrite.toJson(dayCollections, writer);
        }

        // 7. Create a summary report. > Integrate with allure report

    }
}
