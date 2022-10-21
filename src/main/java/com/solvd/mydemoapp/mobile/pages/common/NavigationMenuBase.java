package com.solvd.mydemoapp.mobile.pages.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class NavigationMenuBase extends AbstractPage {

    public NavigationMenuBase(WebDriver driver) {
        super(driver);
    }

    public abstract CartScreenBase openCart();

    public abstract int getProductsAmountInCart();

    public abstract boolean isNoCartCounter();
}
