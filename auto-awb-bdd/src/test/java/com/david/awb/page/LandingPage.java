package com.david.awb.page;

import org.openqa.selenium.support.FindBy;

import com.david.test.core.base.Page;
import com.david.test.core.driver.WebDriverManager;
import com.david.test.core.element.WElement;

/** Very first coding sample of POM */
public class LandingPage extends Page {

    public LandingPage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    @FindBy(xpath = "//span[contains(text(),'Hey there!')]")
    WElement helloMsg;

    @FindBy(xpath = "//span[contains(text(),'Tracking')]")
    WElement btnTracking;

    @FindBy(xpath = "//span[contains(text(),'Got it!')]/ancestor::button")
    WElement btnGotIt;

    public boolean isHelloMsgAppear() {
        return helloMsg.isDisplayed();
    }

    public void clickTracking() {
        // Check if need to click GOT IT
        if (btnGotIt.isDisplayed()) btnGotIt.click();
        btnTracking.click();
    }
}
