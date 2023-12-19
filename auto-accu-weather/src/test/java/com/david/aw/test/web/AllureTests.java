package com.david.aw.test.web;

import org.testng.annotations.Test;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AllureTests {
    @Test(
            groups = {"regression", "allure"},
            description = "TC[Allure] test allure 1")
    public void testTheAllureReport() {
        playAround("Hello 1");
        playAround("Hello 2");
        workAround("Hello 1");
        workAround("Hello 2");
    }

    @Test(
            groups = {"regression", "allure"},
            description = "TC[Allure] test allure 2")
    public void testTheAllureReport2() {
        String testdata = System.getProperty("allure.results.directory");
        System.out.println(testdata);
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
