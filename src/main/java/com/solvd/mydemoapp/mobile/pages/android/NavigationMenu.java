package com.solvd.mydemoapp.mobile.pages.android;

import com.qaprosoft.carina.core.foundation.webdriver.DriverHelper;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.mydemoapp.mobile.pages.common.CartScreenBase;
import com.solvd.mydemoapp.mobile.pages.common.INavigationMenu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class NavigationMenu extends AbstractPage implements INavigationMenu {

    @FindBy(xpath = "//*[@content-desc='View cart']")
    private ExtendedWebElement cartButton;

    @FindBy(xpath = "//android.widget.TextView[contains(@resource-id,'cart')]")
    private ExtendedWebElement cartAmountLabel;

    public NavigationMenu(WebDriver driver) {
        super(driver);
    }

    @Override
    public CartScreenBase openCart() {
        cartButton.click();
        return initPage(getDriver(), CartScreenBase.class);
    }

    @Override
    public int getProductsAmountInCart() {
        return Integer.parseInt(cartAmountLabel.getText());
    }

    @Override
    public boolean isNoCartCounter() {
        return cartAmountLabel.isElementNotPresent(DriverHelper.SHORT_TIMEOUT);
    }
}
