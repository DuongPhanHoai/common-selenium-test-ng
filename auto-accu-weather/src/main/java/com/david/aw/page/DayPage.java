package com.david.aw.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.david.aw.dto.DayInfo;
import com.david.test.core.base.Page;
import com.david.test.core.element.WElement;

import io.qameta.allure.Step;

public class DayPage extends Page {
    public DayPage(RemoteWebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".module-title")
    WElement moduleTitle;

    @FindBy(xpath = "//div[@class='page-content content-module']")
    WElement moduleContent;

    @FindBy(xpath = "//*[contains(@class,'daily-forecast-card')]//*[@class='icon']")
    WElement firstDayDetail;

    @Step("Get the Day title")
    public String getTitle() {
        return moduleTitle.getText();
    }

    @Step("Get all day information")
    public List<DayInfo> getAllDayInfos() {
        List<DayInfo> dayInfos = new ArrayList<>();
        List<WebElement> dayElements =
                moduleContent.findElements(new By.ByXPath("//*[@class='module-header sub date']"));
        for (WebElement dayElement : dayElements) {
            dayInfos.add(DayInfo.builder().day(dayElement.getText()).build());
        }
        return dayInfos;
    }

    @Step("Click to the first day arrow button")
    public void clickFirstDayDetail() {
        firstDayDetail.click();
    }
}
