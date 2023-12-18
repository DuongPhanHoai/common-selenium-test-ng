package com.david.aw.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    @FindBy(css = ".page-content.content-module")
    WElement moduleContent;

    @FindBy(xpath = "//*[contains(@class,'daily-forecast-card')]//*[@class='icon']")
    WElement firstDayDetail;

    public String getTitle() {
        return moduleTitle.getText();
    }

    public List<DayInfo> getAllDayInfos() {
        List<DayInfo> dayInfos = new ArrayList<>();
        List<WebElement> dayElements =
                moduleContent.findElements(new By.ByClassName("daily-wrapper"));
        for (WebElement dayElement : dayElements) {
            dayInfos.add(
                    DayInfo.builder()
                            .day(
                                    dayElement
                                            .findElement(
                                                    new By.ByXPath(
                                                            "//span[@class='module-header sub date']"))
                                            .getText())
                            .build());
        }
        return dayInfos;
    }

    public boolean clickDayDetail(String dayValue) {
        List<WebElement> dayElements =
                moduleContent.findElements(new By.ByClassName("daily-wrapper"));
        for (WebElement dayElement : dayElements) {
            String dayTitle = dayElement.findElement(new By.ByClassName("date")).getText();
            if (dayTitle.equals(dayValue)) {
                WebElement found =
                        dayElement
                                .findElement(new By.ByClassName("daily-forecast-card"))
                                .findElement(new By.ByTagName("svg"));
                // scroll to screen before click
                Actions actions = new Actions(driverManager.getDriver());
                actions.moveToElement(found);
                actions.perform();
                found.click();
                return true;
            }
        }
        return false;
    }

    public void clickFirstDayDetail() {
        firstDayDetail.click();
    }
}
