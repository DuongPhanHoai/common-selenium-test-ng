package com.david.aw.test.web;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import report.HistoryListener;

@Listeners({HistoryListener.class})
@Slf4j
public class HistoryTests {
    @Test(
            groups = {"regression", "allure"},
            description = "TC[Allure] test allure 1")
    public void testScenarioLogin() {
        playAround("Hello 1");
        playAround("Hello 2");
        workAround("Hello 1");
        workAround("Hello 2");
    }

    @Test(
            groups = {"regression", "allure"},
            description = "TC[Allure] test allure 2")
    public void testScenarioHomepage() {
        playAround("Hello 1");
        workAround("Hello 1");
        playAround("Hello 2");
        workAround("Hello 2");
    }

    @Step("Play around")
    public static void playAround(String input) {
        log.info("Play around for report {}", input);
    }

    @Step("Play around")
    public static void workAround(String input) {
        log.info("Work around for report {}", input);
    }
}
