package com.solvd.mydemoapp.mobile.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.automationpractice.utils.StringUtils;
import com.solvd.mydemoapp.mobile.pages.common.CartScreenBase;
import com.solvd.mydemoapp.mobile.pages.common.CatalogScreenBase;
import com.solvd.mydemoapp.mobile.pages.common.INavigationMenu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CartScreenBase.class)
public class CartScreen extends CartScreenBase {

    @FindBy(xpath = "//*[text()='My Cart']")
    private ExtendedWebElement title;

    @FindBy(xpath = "//android.view.ViewGroup")
    private List<ExtendedWebElement> productItems;

    @FindBy(xpath = "//*[contains(@resource-id,'minus')]")
    private List<ExtendedWebElement> minusQuantityButtons;

    @FindBy(xpath = "//*[contains(@resource-id,'plus')]")
    private List<ExtendedWebElement> plusQuantityButtons;

    @FindBy(xpath = "//*[contains(@resource-id,'title')]")
    private List<ExtendedWebElement> productNameLabels;

    @FindBy(xpath = "//*[contains(@resource-id,'price')]")
    private List<ExtendedWebElement> productPriceLabels;

    @FindBy(xpath = "//*[contains(@resource-id,'remove')]")
    private List<ExtendedWebElement> removeItemButtons;

    @FindBy(xpath = "//*[contains(@content-desc,'checkout')]")
    private ExtendedWebElement proceedToCheckoutButton;

    @FindBy(xpath = "//*[contains(@resource-id,'items')]")
    private ExtendedWebElement totalQuantityLabel;

    @FindBy(xpath = "//*[contains(@resource-id,'totalPrice')]")
    private ExtendedWebElement totalAmountLabel;

    @FindBy(xpath = "//*[contains(@resource-id,'noItemTitle')]")
    private ExtendedWebElement noItemsLabel;

    @FindBy(xpath = "//*[contains(@resource-id,'shopping')]")
    private ExtendedWebElement goShoppingButton;

    public CartScreen(WebDriver driver) {
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
    public CartScreenBase increaseProductQuantity(String productName, int count) {
        int productIndex = getProductIndexByName(productName);
        for (int i = 0; i < count; i++) {
            plusQuantityButtons.get(productIndex).click();
        }
        return initPage(getDriver(), CartScreenBase.class);
    }

    @Override
    public CartScreenBase decreaseProductQuantity(String productName, int count) {
        int productIndex = getProductIndexByName(productName);
        for (int i = 0; i < count; i++) {
            minusQuantityButtons.get(productIndex).click();
        }
        return initPage(getDriver(), CartScreenBase.class);
    }

    @Override
    public CartScreenBase removeProduct(String productName) {
        removeItemButtons.get(getProductIndexByName(productName)).click();
        return initPage(getDriver(), CartScreenBase.class);
    }

    @Override
    public boolean isCartEmpty() {
        return noItemsLabel.isElementPresent();
    }

    @Override
    public CatalogScreenBase clickGoShoppingButton() {
        goShoppingButton.click();
        return initPage(getDriver(), CatalogScreenBase.class);
    }

    @Override
    public boolean isOpened() {
        return proceedToCheckoutButton.isElementPresent();
    }

    @Override
    public INavigationMenu getNavigation() {
        return new NavigationMenu(getDriver());
    }

    private int getProductIndexByName(String productName) {
        for (int i = 0; i < productNameLabels.size(); i++) {
            if (productNameLabels.get(i).getText().equals(productName)) {
                return i;
            }
        }
        throw new RuntimeException("Product " + productName + " not found");
    }
}
