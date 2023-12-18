package com.david.test.core.element;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.david.test.core.driver.DriverManager;

public class WElement extends Element implements WebElement {
    protected final Logger LOG = LoggerFactory.getLogger(WElement.class);
    String instanceString;
    String name;

    public WElement(DriverManager driverManager, String name) {
        super(driverManager, name);
        this.name = name;
        instanceString = name;
    }

    /**
     * Overide setBy to create instanceString more detail
     *
     * @param by setBy to create instanceString more detail
     */
    public void setBy(By by) {
        instanceString = String.format("%s[%s]", name, by);
        super.setBy(by);
    }

    WebElement findElement() {
        try {
            return driverManager.getWait().until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (TimeoutException e) {
            LOG.debug("Get  Exception ", e);
            throw new TimeoutException("Unable to find " + instanceString);
        }
    }

    @Override
    public void click() {
        driverManager
                .getWait()
                .until(ExpectedConditions.elementToBeClickable(findElement()))
                .click();
        LOG.info("Click {}", instanceString);
        DriverManager.sleep(2);
    }

    @Override
    public void submit() {}

    @Override
    public void sendKeys(CharSequence... charSequences) {
        findElement().sendKeys(charSequences);
        LOG.info("sendKeys {} to {}", charSequences, instanceString);
        DriverManager.sleepMilliseconds(500);
    }

    @Override
    public void clear() {}

    @Override
    public String getTagName() {
        return null;
    }

    @Override
    public String getAttribute(String s) {
        return findElement().getAttribute(s);
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
        return driverManager
                .getWait()
                .until(ExpectedConditions.visibilityOf(findElement()))
                .getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return findElement().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return findElement().findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        try {
            WebElement e =
                    driverManager.getWait().until(ExpectedConditions.presenceOfElementLocated(by));
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

    public boolean isDisplayed(int second) {
        try {
            WebElement e =
                    new WebDriverWait(driverManager.getDriver(), second)
                            .until(ExpectedConditions.presenceOfElementLocated(by));
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
