package com.david.google.page;

import org.openqa.selenium.support.FindBy;

import com.david.test.core.base.Page;
import com.david.test.core.driver.Driver;
import com.david.test.core.element.WElement;
import com.david.test.core.util.LogUtils;

/** Very first coding sample of POM */
public class GooglePage extends Page {

    public GooglePage(Driver driver) {
        super(driver);
    }

    @FindBy(name = "q")
    WElement questionElement;

    @FindBy(name = "btnK")
    WElement btnSearch;

    public void openGoogle() {
        try {
            driver.get("htp://google.com");
            if (btnSearch.isDisplayed()) {
                LOG.info("Successfully opened Google");
            }
        } catch (Exception e) {
            LOG.debug("Exception on open GOOGLE: {}", LogUtils.getFullStack(e));
        }
    }

    public void ask(String question) {
        questionElement.sendKeys(question);
        btnSearch.click();
        LOG.info("Clicked the logout button");
    }
}
