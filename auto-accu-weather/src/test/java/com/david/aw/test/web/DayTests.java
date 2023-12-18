package com.david.aw.test.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.david.aw.dto.DayInfo;
import com.david.aw.page.DayDetailPage;
import com.david.aw.page.DayPage;
import com.david.aw.page.HomePage;
import com.david.aw.test.BaseAWTest;

public class DayTests extends BaseAWTest {
    protected final Logger log = LoggerFactory.getLogger(DayTests.class);

    @Test
    public void scanAllDays() {
        // Declare Pages
        HomePage homePage = new HomePage(getWebDriverManager());
        DayPage dayPage = new DayPage(getWebDriverManager());
        DayDetailPage dayDetailPage = new DayDetailPage(getWebDriverManager());

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
        for (int i = 1; i < dayInfos.size(); i++) {
            DayInfo dayInfo = dayInfos.get(i);
            log.info("scanning {}", dayInfo.getDay());
            Assert.assertEquals(dayInfo.getDay(), dayDetailPage.clickNextDay());
            // get Day (Morning or Afternoon)
            dayInfo.setDayWeatherInfos(dayDetailPage.getDayInfo());
        }
    }
}
