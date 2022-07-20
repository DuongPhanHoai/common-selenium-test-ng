package com.david.awb.test.web;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.david.awb.page.LandingPage;
import com.david.awb.page.LoginPage;
import com.david.awb.page.OrderDetailPage;
import com.david.awb.page.TrackingPage;
import com.david.awb.test.data.ConfigReader;
import com.david.test.core.BaseWebTest;
import com.david.test.core.dto.ServerInfo;
import com.david.test.core.util.LogUtils;

/** Verify fist sample web test sample: - Extend the BaseWebTest - Using the POM */
public class AWBTests extends BaseWebTest {
    ServerInfo serverInfo = ConfigReader.getServerInfo();

    @Test
    public void printAWB() {
        // Page declare
        LoginPage loginPage = new LoginPage(getWebDriverManager());
        LandingPage landingPage = new LandingPage(getWebDriverManager());
        TrackingPage trackingPage = new TrackingPage(getWebDriverManager());
        OrderDetailPage orderDetailPage = new OrderDetailPage(getWebDriverManager());

        // Clean all file in download dir
        File downloadDirFile = new File(getDownloadDir());
        try {
            FileUtils.cleanDirectory(downloadDirFile);
        } catch (Exception e) {
            LOG.warn(LogUtils.getFullStack(e));
        }

        // Given Shipper opened the Ninja Dashboard login page at
        // https://dashboard-qa.ninjavan.co/login-v2
        // Shipper log-in
        loginPage.login(serverInfo);

        // Then shipper arrived at landing page
        landingPage.isHelloMsgAppear();

        // When shipper go to the tracking page
        landingPage.clickTracking();

        // When shipper go to the tracking page And shipper click search button
        trackingPage.clickSearchForParcel();

        // And shipper select the first tracking ID in the table
        String orderId = trackingPage.clickTheFirstFoundResult();

        // Then QA verify that the Order Details page is shown
        getWebDriverManager().switchToTabIndex(1);
        String detailOrderId = orderDetailPage.getTrackingId();
        Assert.assertEquals(orderId, detailOrderId, "Compare the OrderId");

        // When shipper click Print Airway Bill button
        orderDetailPage.clickPrintAirwayBill();

        // And shipper select 1 bills per page in Print Airway Bills dialog
        orderDetailPage.click1BillsPerPage();

        // And shipper click print button in Print Airway Bills dialog
        String hrefResult = orderDetailPage.clickPrint();

        // Then QA verify that the tracking ID text in the pdf is the same as in the step 7
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
    }
}
