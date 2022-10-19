package com.solvd.mydemoapp.mobile.pages.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class CartPageBase extends AbstractPage {
    public CartPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract int getTotalQuantity();

    public abstract double getTotalPrice();

    public abstract void clickProceedToCheckoutButton();

    public abstract List<String> getAddedProductNames();
}
