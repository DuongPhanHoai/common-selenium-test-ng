package com.sauce.test.test.web;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sauce.test.BaseSourceWebTest;
import com.sauce.test.page.CartPage;
import com.sauce.test.page.HomePage;
import com.sauce.test.page.LoginPage;
import com.sauce.test.page.ProductDetailPage;

/** Verify fist sample web test sample: - Extend the BaseSourceWebTest - Using the POM */
public class LandingTests extends BaseSourceWebTest {
    /**
     * Steps: - Open page, login with valid user - Click to the product "Sauce Labs Onesie" in list
     * - Click Add To Cart Verify: - Open Cart and verify Adding successful
     */
    @Test
    public void addToCart() {
        // Declare Pages
        LoginPage loginPage = new LoginPage(getWebDriverManager());
        HomePage homePage = new HomePage(getWebDriverManager());
        ProductDetailPage productDetailPage = new ProductDetailPage(getWebDriverManager());
        CartPage cartPage = new CartPage(getWebDriverManager());

        // Open page, login with valid user
        loginPage.login(serverInfo);

        // Click to the product "Sauce Labs Onesie" in list
        homePage.clickTheProduct("Sauce Labs Onesie");

        // Click Add To Cart
        productDetailPage.clickAddToCart();

        // OpenCart
        homePage.clickShoppingCartLink();

        // verify Adding successful
        Assert.assertTrue(cartPage.findItemInCart("Sauce Labs Onesie"), "Find Item in Cart");
    }
}
