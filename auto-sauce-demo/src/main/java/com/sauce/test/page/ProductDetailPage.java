package com.sauce.test.page;

import org.openqa.selenium.support.FindBy;

import com.david.test.core.base.Page;
import com.david.test.core.driver.DriverManager;
import com.david.test.core.element.WElement;

public class ProductDetailPage extends Page {

    public ProductDetailPage(DriverManager driverManager) {
        super(driverManager);
    }

    @FindBy(css = ".inventory_details_container .inventory_details_desc_container .btn")
    WElement addToCart;

    public void clickAddToCart() {
        addToCart.click();
    }
}
