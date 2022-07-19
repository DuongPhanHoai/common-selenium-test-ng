package com.david.google.test.web;

import org.testng.annotations.Test;

import com.david.google.page.GooglePage;
import com.david.test.core.BaseWebTest;

/** Verify fist sample web test sample: - Extend the BaseWebTest - Using the POM */
public class TryOnFieldTests extends BaseWebTest {
    GooglePage googlePage = new GooglePage(getDriver());

    @Test
    public void askQuestion1() {

        // Launch Page
        googlePage.openGoogle();

        // Asking something
        googlePage.ask("I ask the first question");
    }
}
