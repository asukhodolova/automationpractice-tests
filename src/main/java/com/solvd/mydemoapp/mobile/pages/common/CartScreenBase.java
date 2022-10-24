package com.solvd.mydemoapp.mobile.pages.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class CartScreenBase extends AbstractPage implements ICommonScreen {

    public CartScreenBase(WebDriver driver) {
        super(driver);
    }

    public abstract int getTotalQuantity();

    public abstract double getTotalPrice();

    public abstract void clickProceedToCheckoutButton();

    public abstract List<String> getAddedProductNames();

    public abstract CartScreenBase increaseProductQuantity(String productName, int count);

    public abstract CartScreenBase decreaseProductQuantity(String productName, int count);

    public abstract CartScreenBase removeProduct(String productName);

    public abstract boolean isCartEmpty();

    public abstract CatalogScreenBase clickGoShoppingButton();
}
