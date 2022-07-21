package com.david.awb.page;

import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.david.test.core.base.Page;
import com.david.test.core.driver.WebDriverManager;
import com.david.test.core.element.WElement;

public class OrderDetailPage extends Page {
    public OrderDetailPage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    @FindBy(xpath = "//div[@testid='tracking-id']")
    WElement trackingId;

    @FindBy(xpath = "//*[contains(text(),'Print Airway Bill')]/ancestor::button")
    WElement btnPrintAirwayBill;

    @FindBy(xpath = "//span[contains(text(),'1 bills per page')]")
    WElement btn1BillsPerPage;

    @FindBy(xpath = "//span[contains(text(),'print')]/ancestor::a")
    WElement btnPrint;

    public String getTrackingId() {
        Assert.assertTrue(trackingId.isDisplayed());
        return trackingId.getText();
    }

    public void clickPrintAirwayBill() {
        btnPrintAirwayBill.click();
    }

    public void click1BillsPerPage() {
        btn1BillsPerPage.click();
    }

    /** @return the href of pdf file */
    public String clickPrint() {
        String href = btnPrint.getAttribute("href");
        btnPrint.click();
        return href;
    }
}
