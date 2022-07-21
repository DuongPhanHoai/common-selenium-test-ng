package com.david.bdd.steps.web;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.david.awb.data.ConfigReader;
import com.david.awb.driver.DriverUtils;
import com.david.awb.page.LandingPage;
import com.david.awb.page.LoginPage;
import com.david.awb.page.OrderDetailPage;
import com.david.awb.page.TrackingPage;
import com.david.test.core.driver.WebDriverManager;
import com.david.test.core.dto.ServerInfo;
import com.david.test.core.util.LogUtils;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AWBStepDef {
    protected static final Logger LOG = LoggerFactory.getLogger(AWBStepDef.class);
    // The configuration can be read from file (url, usr, pwd)
    ServerInfo serverInfo = ConfigReader.getServerInfo();

    // Declare Driver
    WebDriverManager webDriverManager;

    // Declare Page Objects
    LoginPage loginPage;
    LandingPage landingPage;
    TrackingPage trackingPage;
    OrderDetailPage orderDetailPage;
    File downloadDirFile = new File(DriverUtils.getDownloadDir());

    @Given("Given Shipper opened the Ninja Dashboard login page at {}")
    public void openBrowser(String serverAddress) {
        try {
            // Clean all file in download dir
            try {
                FileUtils.cleanDirectory(downloadDirFile);
            } catch (Exception e) {
                LOG.warn(LogUtils.getFullStack(e));
            }
            // Start new Browser
            webDriverManager = DriverUtils.getNewDriver("chrome");
            webDriverManager.getDriver().get(serverAddress);
            loginPage = new LoginPage(webDriverManager);
            landingPage = new LandingPage(webDriverManager);
            trackingPage = new TrackingPage(webDriverManager);
            orderDetailPage = new OrderDetailPage(webDriverManager);
        } catch (Exception e) {
            LOG.debug("Exception on open GOOGLE: {}", LogUtils.getFullStack(e));
        }
    }

    @And("shipper had an order created under his account")
    public void shipperHadAnOrder() {}

    @When("shipper login by using credentials {} and {}")
    public void login(String email, String pwd) {
        loginPage.login(email, pwd);
    }

    @Then("shipper arrived at landing page")
    public void shipperArrivedAtLandingPage() {
        landingPage.isHelloMsgAppear();
    }

    @When("shipper go to the tracking page")
    public void shipperGoToTheTrackingPage() {
        landingPage.clickTracking();
    }

    @And("shipper click search button")
    public void shipperClickSearchButton() {
        trackingPage.clickSearchForParcel();
    }

    String orderId;

    @And("shipper select the first tracking ID in the table")
    public void shipperSelectTheFirstTrackingIDInTheTable() {
        orderId = trackingPage.clickTheFirstFoundResult();
    }

    @Then("QA verify that the Order Details page is shown")
    public void qaVerifyThatTheOrderDetailsPageIsShown() {
        webDriverManager.switchToTabIndex(1);
        String detailOrderId = orderDetailPage.getTrackingId();
        Assert.assertEquals(orderId, detailOrderId, "Compare the OrderId");
    }

    @When("shipper click Print Airway Bill button")
    public void shipperClickPrintAirwayBillButton() {
        orderDetailPage.clickPrintAirwayBill();
    }

    @And("shipper select {int} bills per page in Print Airway Bills dialog")
    public void shipperSelectBillsPerPageInPrintAirwayBillsDialog(int arg0) {
        orderDetailPage.click1BillsPerPage();
    }

    @And("shipper click print button in Print Airway Bills dialog")
    public void shipperClickPrintButtonInPrintAirwayBillsDialog() {
        String hrefResult = orderDetailPage.clickPrint();
    }

    @Then("QA verify that the tracking ID text in the pdf is the same as in the step {int}")
    public void qaVerifyThatTheTrackingIDTextInThePdfIsTheSameAsInTheStep(int arg0) {
        boolean foundText = false;
        try {
            Collection<File> pdfFiles =
                    FileUtils.listFiles(downloadDirFile, new String[] {"pdf"}, false);
            for (File pdfFile : pdfFiles) {
                PDDocument document = PDDocument.load(pdfFile);
                PDFTextStripper stripper = new PDFTextStripper();
                String text = stripper.getText(document).replaceAll("[^A-Za-z0-9. ]+", "");
                Assert.assertTrue(text.indexOf(orderId) > 0);
                foundText = true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(foundText, "Verify the orderId in PDF");
        webDriverManager.end();
    }
}
