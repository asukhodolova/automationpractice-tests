package com.solvd.mydemoapp.mobile.pages.ios;

import com.qaprosoft.carina.core.foundation.webdriver.DriverHelper;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.locator.ExtendedFindBy;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.mydemoapp.mobile.pages.common.CartScreenBase;
import com.solvd.mydemoapp.mobile.pages.common.INavigationMenu;
import org.openqa.selenium.WebDriver;

public class NavigationMenu extends AbstractPage implements INavigationMenu {

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeButton[`name == 'Cart-tab-item'`]")
    private ExtendedWebElement cartButton;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[-1]/XCUIElementTypeStaticText")
    private ExtendedWebElement cartAmountLabel;

    public NavigationMenu(WebDriver driver) {
        super(driver);
    }

    public CartScreenBase openCart() {
        cartButton.click();
        return initPage(getDriver(), CartScreenBase.class);
    }

    public int getProductsAmountInCart() {
        return Integer.valueOf(cartAmountLabel.getAttribute("name"));
    }

    @Override
    public boolean isNoCartCounter() {
        return cartAmountLabel.isElementNotPresent(DriverHelper.SHORT_TIMEOUT);
    }

}
