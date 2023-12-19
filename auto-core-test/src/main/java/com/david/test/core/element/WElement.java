package com.david.test.core.element;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.david.test.core.util.TimeUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WElement extends Element implements WebElement {
    WebDriverWait wait;
    String instanceString;
    String name;

    public WElement(RemoteWebDriver driver, String name) {
        super(driver, name);
        this.wait = new WebDriverWait(driver, TimeUtil.WAIT_SEC, TimeUtil.INTERVAL_MILLI_SEC);
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
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (TimeoutException e) {
            log.debug("Get  Exception ", e);
            throw new TimeoutException("Unable to find " + instanceString);
        }
    }

    static final int MAX_TRY_CLICK = 5;

    static final int CLICK_SLEEP_MILLISECOND = 1280;
    @Override
    public void click() {
        for (int i = 0; i < MAX_TRY_CLICK; i++) {
            try {
                Actions actions = new Actions(driver);
                actions.moveToElement(findElement()).click().build().perform();
                log.info("Click {}", instanceString);
                TimeUtil.sleepMilliseconds(CLICK_SLEEP_MILLISECOND);
                return;
            } catch (Exception e) {
                log.info("Failed on click {}:", instanceString, e);
            }
            TimeUtil.sleep(CLICK_SLEEP_MILLISECOND);
        }
    }

    @Override
    public void submit() {}

    @Override
    public void sendKeys(CharSequence... charSequences) {
        findElement().sendKeys(charSequences);
        log.info("sendKeys {} to {}", charSequences, instanceString);
        TimeUtil.sleepMilliseconds(500);
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
        return wait.until(ExpectedConditions.visibilityOf(findElement())).getText();
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
            WebElement e = wait.until(ExpectedConditions.presenceOfElementLocated(by));
            if (e.isDisplayed()) {
                log.info("{} displayed", instanceString);
                return true;
            } else {
                log.info("{} did not displayed", instanceString);
                return false;
            }
        } catch (TimeoutException e) {
            log.debug("Get WElement Exception ", e);
            return false;
        }
    }

    public boolean isDisplayed(int second) {
        try {
            WebElement e =
                    new WebDriverWait(driver, second)
                            .until(ExpectedConditions.presenceOfElementLocated(by));
            if (e.isDisplayed()) {
                log.info("{} displayed", instanceString);
                return true;
            } else {
                log.info("{} did not displayed", instanceString);
                return false;
            }
        } catch (TimeoutException e) {
            log.debug("Get WElement Exception ", e);
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

    /** Quick support findByCurrentDriver Please note that not find the child of Element */
    public List<WebElement> quickDriverFindByXpath(String xpath) {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        List<WebElement> foundItems = driver.findElementsByXPath(xpath);
        driver.manage().timeouts().implicitlyWait(TimeUtil.WAIT_SEC, TimeUnit.SECONDS);
        return foundItems;
    }
}
