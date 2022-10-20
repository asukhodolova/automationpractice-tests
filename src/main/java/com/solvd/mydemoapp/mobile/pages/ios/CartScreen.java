package com.solvd.mydemoapp.mobile.pages.ios;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.locator.ExtendedFindBy;
import com.solvd.automationpractice.utils.StringUtils;
import com.solvd.mydemoapp.mobile.pages.common.CartScreenBase;
import com.solvd.mydemoapp.mobile.pages.common.CatalogScreenBase;
import com.solvd.mydemoapp.mobile.pages.common.INavigationMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CartScreenBase.class)
public class CartScreen extends CartScreenBase {

    @ExtendedFindBy(iosPredicate = "name == 'My Cart'")
    private ExtendedWebElement title;

    @ExtendedFindBy(iosPredicate = "type == 'XCUIElementTypeCell'")
    private List<ExtendedWebElement> productItems;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeCell/XCUIElementTypeButton[`name == 'SubtractMinus Icons'`]")
    private List<ExtendedWebElement> minusQuantityButtons;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeCell/XCUIElementTypeButton[`name == 'AddPlus Icons'`]")
    private List<ExtendedWebElement> plusQuantityButtons;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeCell/XCUIElementTypeStaticText[`NOT(name CONTAINS '$')`]")
    private List<ExtendedWebElement> productNameLabels;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeCell/XCUIElementTypeStaticText[`name CONTAINS '$'`]")
    private List<ExtendedWebElement> productPriceLabels;

    @ExtendedFindBy(iosPredicate = "name == 'Remove Item'")
    private List<ExtendedWebElement> removeItemButtons;

    @ExtendedFindBy(iosPredicate = "name == 'ProceedToCheckout'")
    private ExtendedWebElement proceedToCheckoutButton;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText[`name CONTAINS 'Items' AND NOT (name CONTAINS 'No')`]")
    private ExtendedWebElement totalQuantityLabel;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther/XCUIElementTypeStaticText[`name CONTAINS '$'`]")
    private ExtendedWebElement totalAmountLabel;

    @ExtendedFindBy(iosPredicate = "name == 'No Items'")
    private ExtendedWebElement noItemsLabel;

    @ExtendedFindBy(iosPredicate = "name == 'Go Shopping'")
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
        return productItems.stream()
                .map(p -> p.findExtendedWebElement(By.xpath(".//XCUIElementTypeStaticText"))
                        .getText()).collect(Collectors.toList());
        // FIXME impossible to use following-sibling in ios class chain
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

    private int getProductIndexByName(String productName) {
        for (int i = 0; i < productNameLabels.size(); i++) {
            if (productNameLabels.get(i).getText().equals(productName)) {
                return i;
            }
        }
        throw new RuntimeException("Product " + productName + " not found");
    }


    @Override
    public boolean isOpened() {
        return title.isElementPresent();
    }

    @Override
    public INavigationMenu getNavigation() {
        return new NavigationMenu(getDriver());
    }
}
