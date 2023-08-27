package com.sauce.test.page;

import java.util.List;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.david.test.core.base.Page;
import com.david.test.core.driver.DriverManager;
import com.david.test.core.element.WElement;

public class HomePage extends Page {
    public HomePage(DriverManager driverManager) {
        super(driverManager);
    }

    @FindBy(id = "inventory_container")
    WElement inventoryContainer;

    @FindBy(css = ".shopping_cart_link")
    WElement shoppingCartLink;

    public boolean isInventoryContainerAppear() {
        return inventoryContainer.isDisplayed();
    }

    public WebElement findTheProduct(String productName) {
        List<WebElement> productElements =
                inventoryContainer.findElements(new By.ByClassName("inventory_item"));
        for (WebElement scanElement : productElements) {
            String productText =
                    scanElement.findElement(new By.ByClassName("inventory_item_name")).getText();
            if (productName.equals(productText)) {
                return scanElement;
            }
        }
        return null;
    }

    /**
     * Find the product and click
     *
     * @param productName product name
     * @return true if found and click successful
     */
    public void clickTheProduct(String productName) {
        WebElement foundProduct = findTheProduct(productName);
        if (Objects.nonNull(foundProduct)) {
            foundProduct.findElement(new By.ByCssSelector(".inventory_item_label>a")).click();
            return;
        }
        throw new AssertionError("HomePage failed to click the product: " + productName);
    }

    public void clickShoppingCartLink() {
        shoppingCartLink.click();
    }
}
