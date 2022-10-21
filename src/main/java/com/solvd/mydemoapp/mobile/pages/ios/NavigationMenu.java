package com.solvd.mydemoapp.mobile.pages.ios;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.DriverHelper;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.locator.ExtendedFindBy;
import com.solvd.mydemoapp.mobile.pages.common.CartScreenBase;
import com.solvd.mydemoapp.mobile.pages.common.NavigationMenuBase;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = NavigationMenuBase.class)
public class NavigationMenu extends NavigationMenuBase {

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeButton[`name == 'Cart-tab-item'`]")
    private ExtendedWebElement cartButton;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[-1]/XCUIElementTypeStaticText")
    private ExtendedWebElement cartAmountLabel;

    public NavigationMenu(WebDriver driver) {
        super(driver);
    }

    public CartScreenBase openCart() {
        cartButton.click();
        return initPage(CartScreenBase.class);
    }

    public int getProductsAmountInCart() {
        return Integer.valueOf(cartAmountLabel.getAttribute("name"));
    }

    @Override
    public boolean isNoCartCounter() {
        return cartAmountLabel.isElementNotPresent(DriverHelper.SHORT_TIMEOUT);
    }

}
