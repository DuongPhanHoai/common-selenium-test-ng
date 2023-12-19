package com.david.aw.page;

import java.util.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.david.aw.dto.DayWeatherInfo;
import com.david.test.core.base.Page;
import com.david.test.core.element.WElement;
import com.david.test.core.util.TimeUtil;

import io.qameta.allure.Step;

public class DayDetailPage extends Page {
    public DayDetailPage(RemoteWebDriver driver) {
        super(driver);
    }

    static final String DAY_NIGHT_XPATH =
            "//div[contains(@class,'half-day-card ')]//*[@class='quarter-day-ctas']//a[contains(text(),'Day & Night')]";

    @FindBy(xpath = DAY_NIGHT_XPATH)
    WElement dayNightButton;

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

    static final String HUMID_XPATH =
            "//p[contains(text(),'Humidity') and @class='panel-item']//span";

    @FindBy(xpath = HUMID_XPATH)
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

    @Step("Get day information")
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
        List<WebElement> foundItems = driver.findElementsByClassName("half-day-card");
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
            clickDayMorningButton();
            dayLight.setHumidity(humidity.getText());
            dayWeatherInfos.add(dayLight);
            clickDayNight();
        }
        clickNightEveningButton();
        night.setHumidity(humidity.getText());
        dayWeatherInfos.add(night);
        clickDayNight();
        return dayWeatherInfos;
    }

    @Step("Click Day & Night button")
    public void clickDayNight() {
        dayNightButton.click();
        List<WebElement> stillAppears = dayNightButton.quickDriverFindByXpath(DAY_NIGHT_XPATH);
        // Sometime click but not affect (unstable browser) need re-try once
        if (stillAppears.size() > 0) {
            stillAppears.get(0).click();
            TimeUtil.sleep(2);
        }
    }

    @Step("Click Day & Night button")
    public void clickNightEveningButton() {
        nightEveningButton.click();
        // Sometime click but not affect (unstable browser) need re-try once
        if (!humidity.isDisplayed())
            try {
                nightEveningButton.click();
            } catch (Exception e) {
            }
    }

    @Step("Click Day & Night button")
    public void clickDayMorningButton() {
        dayMorningButton.click();
        // Sometime click but not affect (unstable browser) need re-try once
        if (!humidity.isDisplayed())
            try {
                nightEveningButton.click();
            } catch (Exception e) {
            }
    }

    @Step("Go to the next day")
    public String clickNextDay() {
        nextDay.click();
        return shortDate.getText();
    }
}
