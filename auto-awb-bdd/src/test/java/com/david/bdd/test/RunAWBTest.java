package com.david.bdd.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "com.david.bdd.steps",
        plugin = {"pretty"},
        features = "src/test/resources/features/")
public class RunAWBTest {
    @BeforeClass
    public static void setUp() {}

    @AfterClass
    public static void destroy() {}
}
