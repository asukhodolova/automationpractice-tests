package com.solvd.mydemoapp.mobile.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.DriverHelper;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.mydemoapp.mobile.pages.common.CartScreenBase;
import com.solvd.mydemoapp.mobile.pages.common.NavigationMenuBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = NavigationMenuBase.class)
public class NavigationMenu extends NavigationMenuBase {

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
        return initPage(CartScreenBase.class);
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
