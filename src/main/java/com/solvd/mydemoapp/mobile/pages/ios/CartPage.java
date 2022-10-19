package com.solvd.mydemoapp.mobile.pages.ios;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.locator.ExtendedFindBy;
import com.solvd.automationpractice.utils.StringUtils;
import com.solvd.mydemoapp.mobile.pages.common.CartPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CartPageBase.class)
public class CartPage extends CartPageBase {

    @ExtendedFindBy(iosPredicate = "name == 'My Cart'")
    private ExtendedWebElement title;
    @ExtendedFindBy(iosPredicate = "type == 'XCUIElementTypeCell'")
    private List<ExtendedWebElement> productItems;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeCell/XCUIElementTypeButton[`name == 'SubtractMinus Icons'`]")
    private List<ExtendedWebElement> minusQuantityButton;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeCell/XCUIElementTypeButton[`name == 'AddPlus Icons'`]")
    private List<ExtendedWebElement> plusQuantityButton;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeCell/XCUIElementTypeStaticText[`NOT(name CONTAINS '$')`]")
    private List<ExtendedWebElement> productNameLabels;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeCell/XCUIElementTypeStaticText[`name CONTAINS '$'`]")
    private List<ExtendedWebElement> productPriceLabels;
    @ExtendedFindBy(iosPredicate = "name == 'ProceedToCheckout'")
    private ExtendedWebElement proceedToCheckoutButton;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText[`name CONTAINS 'Items' AND NOT (name CONTAINS 'No')`]")
    private ExtendedWebElement totalQuantityLabel;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther/XCUIElementTypeStaticText[`name CONTAINS '$'`]")
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
        return productItems.stream()
                .map(p -> p.findExtendedWebElement(By.xpath(".//XCUIElementTypeStaticText"))
                        .getText()).collect(Collectors.toList());
        // FIXME return productNameLabels.stream().map(p -> p.getText()).collect(Collectors.toList());
    }

    @Override
    public boolean isPageOpened() {
        return title.isElementPresent();
    }

}
