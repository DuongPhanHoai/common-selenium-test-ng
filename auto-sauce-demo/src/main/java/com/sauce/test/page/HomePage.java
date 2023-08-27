package com.sauce.test.page;

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

    public boolean isInventoryContainerAppear() {
        return inventoryContainer.isDisplayed();
    }
}
