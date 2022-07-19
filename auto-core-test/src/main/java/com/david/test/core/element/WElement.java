package com.david.test.core.element;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.david.test.core.driver.Driver;

public class WElement extends Element implements WebElement {
    protected final Logger LOG = LoggerFactory.getLogger(WElement.class);
    String instanceString;

    public WElement(Driver driver, String name) {
        super(driver, name);
        instanceString = name + " as " + by;
    }

    WebElement findElement() {
        try {
            return driver.getWait().until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (TimeoutException e) {
            LOG.debug("Get  Exception ", e);
            throw new TimeoutException("Unable to find " + instanceString);
        }
    }

    @Override
    public void click() {
        findElement().click();
        LOG.info("Click {}", instanceString);
    }

    @Override
    public void submit() {}

    @Override
    public void sendKeys(CharSequence... charSequences) {
        findElement().sendKeys(charSequences);
        LOG.info("sendKeys {} to {}", charSequences, instanceString);
    }

    @Override
    public void clear() {}

    @Override
    public String getTagName() {
        return null;
    }

    @Override
    public String getAttribute(String s) {
        return null;
    }

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public List<WebElement> findElements(By by) {
        return null;
    }

    @Override
    public WebElement findElement(By by) {
        return null;
    }

    @Override
    public boolean isDisplayed() {
        try {
            WebElement e = driver.getWait().until(ExpectedConditions.presenceOfElementLocated(by));
            if (e.isDisplayed()) {
                LOG.info("{} displayed", instanceString);
                return true;
            } else {
                LOG.info("{} did not displayed", instanceString);
                return false;
            }
        } catch (TimeoutException e) {
            LOG.debug("Get WElement Exception ", e);
            return false;
        }
    }

    @Override
    public Point getLocation() {
        return null;
    }

    @Override
    public Dimension getSize() {
        return null;
    }

    @Override
    public Rectangle getRect() {
        return null;
    }

    @Override
    public String getCssValue(String s) {
        return null;
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return null;
    }
}
