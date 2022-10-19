package com.solvd.mydemoapp.mobile.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.automationpractice.utils.StringUtils;
import com.solvd.mydemoapp.mobile.pages.common.CartPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CartPageBase.class)
public class CartPage extends CartPageBase {

    @FindBy(xpath = "//*[text()='My Cart']")
    private ExtendedWebElement title;
    @FindBy(xpath = "//android.view.ViewGroup")
    private List<ExtendedWebElement> productItems;
    @FindBy(xpath = "//*[contains(@resource-id,'minus')]")
    private List<ExtendedWebElement> minusQuantityButton;
    @FindBy(xpath = "//*[contains(@resource-id,'plus')]")
    private List<ExtendedWebElement> plusQuantityButton;
    @FindBy(xpath = "//*[contains(@resource-id,'title')]")
    private List<ExtendedWebElement> productNameLabels;
    @FindBy(xpath = "//*[contains(@resource-id,'price')]")
    private List<ExtendedWebElement> productPriceLabels;
    @FindBy(xpath = "//*[contains(@resource-id,'cartBt')]")
    private ExtendedWebElement proceedToCheckoutButton;
    @FindBy(xpath = "//*[contains(@resource-id,'items')]")
    private ExtendedWebElement totalQuantityLabel;
    @FindBy(xpath = "//*[contains(@resource-id,'totalPrice')]")
    private ExtendedWebElement totalAmountLabel;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public int getTotalQuantity() {
        return Integer.parseInt(StringUtils.getNumbersFromString(totalQuantityLabel.getText()));
    }

    @Override
    public double getTotalPrice() {
        return Double.valueOf(totalAmountLabel.getText().replace("$", "").trim());
    }

    @Override
    public void clickProceedToCheckoutButton() {
        proceedToCheckoutButton.click();
    }

    @Override
    public List<String> getAddedProductNames() {
        return productNameLabels.stream().map(p -> p.getText()).collect(Collectors.toList());
    }

    @Override
    public boolean isPageOpened() {
        return proceedToCheckoutButton.isElementPresent();
    }
}
