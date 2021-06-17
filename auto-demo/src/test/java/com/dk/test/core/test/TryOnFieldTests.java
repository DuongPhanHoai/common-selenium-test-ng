package com.dk.test.core.test;

import org.testng.annotations.Test;

import com.dk.test.web.demo.page.GooglePage;

public class TryOnFieldTests extends BaseWebTest {

    @Test
    public void askQuestion1() {
        GooglePage googlePage = new GooglePage(driver);

        // Launch Page
        googlePage.openGoogle();

        // Asking something
        googlePage.ask("I ask the first question");
    }
}
