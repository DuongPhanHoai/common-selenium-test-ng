package com.dk.test.web.demo.page;

import org.openqa.selenium.support.FindBy;

import com.dk.test.core.driver.Driver;
import com.dk.test.core.element.WElement;
import com.dk.test.core.page.PageElement;
import com.dk.test.core.util.StringUtil;

public class GooglePage extends PageElement {

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
                return;
            }
        } catch (Exception e) {
            LOG.debug("Exception on open GOOGLE: ", StringUtil.getFullStack(e));
        }
    }

    public void ask(String question) {
        questionElement.sendKeys(question);
        btnSearch.click();
        LOG.info("Clicked the logout button");
    }
}
