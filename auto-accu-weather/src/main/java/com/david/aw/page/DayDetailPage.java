package com.david.aw.page;

import java.util.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.david.aw.dto.DayWeatherInfo;
import com.david.test.core.base.Page;
import com.david.test.core.driver.DriverManager;
import com.david.test.core.element.WElement;

public class DayDetailPage extends Page {
    public DayDetailPage(DriverManager driverManager) {
        super(driverManager);
    }

    @FindBy(css = ".pagination-button.chevron-icon.right")
    WElement nextDay;

    @FindBy(css = ".short-date")
    WElement shortDate;

    @FindBy(
            xpath =
                    "//h2[text()='Day']/ancestor::div[contains(@class,'half-day-card ')]//div[@class='temperature']")
    WElement dayTemperature;

    @FindBy(
            xpath =
                    "//h2[text()='Day']/ancestor::div[contains(@class,'half-day-card ')]//div[@class='weather']//*[@class='icon']")
    WElement dayWeatherStatus;

    @FindBy(
            xpath =
                    "//h2[text()='Day']/ancestor::div[contains(@class,'half-day-card ')]//*[@class='real-feel']")
    WElement dayRealFeel;

    @FindBy(
            xpath =
                    "//div[contains(@class,'half-day-card ')]//*[@class='quarter-day-ctas']//a[contains(text(),'Morning') or contains(text(),'Afternoon')]")
    WElement dayMorningButton;

    @FindBy(
            xpath =
                    "//div[contains(@class,'half-day-card ')]//*[@class='quarter-day-ctas']//a[contains(text(),'Day & Night')]")
    WElement dayNightButton;

    @FindBy(xpath = "//p[contains(text(),'Humidity') and @class='panel-item']//span")
    WElement humidity;

    @FindBy(
            xpath =
                    "//h2[text()='Night']/ancestor::div[contains(@class,'half-day-card ')]//div[@class='temperature']")
    WElement nightTemperature;

    @FindBy(
            xpath =
                    "//h2[text()='Night']/ancestor::div[contains(@class,'half-day-card ')]//div[@class='weather']//*[@class='icon']")
    WElement nightWeatherStatus;

    @FindBy(
            xpath =
                    "//h2[text()='Night']/ancestor::div[contains(@class,'half-day-card ')]//*[@class='real-feel']")
    WElement nightRealFeel;

    @FindBy(
            xpath =
                    "//div[contains(@class,'half-day-card ')]//*[@class='quarter-day-ctas']//a[contains(text(),'Evening') or contains(text(),'Overnight')]")
    WElement nightEveningButton;

    public List<DayWeatherInfo> getDayInfo() {
        List<DayWeatherInfo> dayWeatherInfos = new ArrayList<DayWeatherInfo>();
        DayWeatherInfo night =
                DayWeatherInfo.builder()
                        .type("Night")
                        .temperature(nightTemperature.getText())
                        .weatherStatus(
                                DayWeatherInfo.convertDataToStatus(
                                        nightWeatherStatus.getAttribute("data-src")))
                        .dayRealFeel(nightRealFeel.getText())
                        .build();
        DayWeatherInfo dayLight = null;
        List<WebElement> foundItems =
                driverManager.getDriver().findElementsByClassName("half-day-card");
        if (foundItems.size() > 1 && dayTemperature.isDisplayed(1)) {
            dayLight =
                    DayWeatherInfo.builder()
                            .type("Day")
                            .temperature(dayTemperature.getText())
                            .weatherStatus(
                                    DayWeatherInfo.convertDataToStatus(
                                            dayWeatherStatus.getAttribute("data-src")))
                            .dayRealFeel(dayRealFeel.getText())
                            .build();
        }
        // Get humid
        if (Objects.nonNull(dayLight)) {
            dayMorningButton.click();
            dayLight.setHumidity(humidity.getText());
            dayWeatherInfos.add(dayLight);
            dayNightButton.click();
        }
        nightEveningButton.click();
        night.setHumidity(humidity.getText());
        dayWeatherInfos.add(night);
        dayNightButton.click();
        return dayWeatherInfos;
    }

    public String clickNextDay() {
        nextDay.click();
        return shortDate.getText();
    }
}
