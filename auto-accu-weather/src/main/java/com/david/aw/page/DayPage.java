package com.david.aw.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.david.aw.dto.DayInfo;
import com.david.test.core.base.Page;
import com.david.test.core.driver.DriverManager;
import com.david.test.core.element.WElement;

public class DayPage extends Page {
    public DayPage(DriverManager driverManager) {
        super(driverManager);
    }

    @FindBy(css = ".module-title")
    WElement moduleTitle;

    @FindBy(xpath = "//div[@class='page-content content-module']")
    WElement moduleContent;

    @FindBy(xpath = "//*[contains(@class,'daily-forecast-card')]//*[@class='icon']")
    WElement firstDayDetail;

    public String getTitle() {
        return moduleTitle.getText();
    }

    public List<DayInfo> getAllDayInfos() {
        List<DayInfo> dayInfos = new ArrayList<>();
        List<WebElement> dayElements =
                moduleContent.findElements(new By.ByXPath("//*[@class='module-header sub date']"));
        for (WebElement dayElement : dayElements) {
            dayInfos.add(DayInfo.builder().day(dayElement.getText()).build());
        }
        return dayInfos;
    }

    public void clickFirstDayDetail() {
        firstDayDetail.click();
    }
}
