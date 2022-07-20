package com.david.awb.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.david.test.core.base.Page;
import com.david.test.core.driver.WebDriverManager;
import com.david.test.core.element.WElement;

public class TrackingPage extends Page {

    public TrackingPage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    @FindBy(xpath = "//div[@class='ant-row-flex']//input[@type='text']")
    WElement txtSearchForParcel;

    @FindBy(xpath = "//div[@class='ant-row-flex']//button")
    WElement btnSearchForParcel;

    @FindBy(css = ".ReactVirtualized__Grid")
    WElement gridResult;

    public void clickSearchForParcel() {
        btnSearchForParcel.click();
    }

    public String clickTheFirstFoundResult() {

        WebElement found =
                gridResult.findElement(By.cssSelector(".ReactVirtualized__Table__row.row"));

        Assert.assertNotNull(found, "Find the first Element");
        // Get Cells
        List<WebElement> colElements =
                found.findElements(By.cssSelector(".ReactVirtualized__Table__rowColumn"));
        String orderId = colElements.get(1).getText();
        found.click();
        return orderId;
    }
}
