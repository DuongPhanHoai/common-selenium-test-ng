package com.sauce.test.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.david.test.core.base.Page;
import com.david.test.core.driver.DriverManager;
import com.david.test.core.element.WElement;

public class CartPage extends Page {
    public CartPage(DriverManager driverManager) {
        super(driverManager);
    }

    @FindBy(className = "cart_list")
    WElement cartList;

    public boolean findItemInCart(String itemText) {
        List<WebElement> cartItems =
                cartList.findElements(new By.ByClassName("inventory_item_name"));
        for (WebElement cartItem : cartItems) {
            String scanItemText = cartItem.getText();
            if (itemText.equals(scanItemText)) return true;
        }
        return false;
    }
}
