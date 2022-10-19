package com.solvd.mydemoapp.mobile.pages.ios;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.locator.ExtendedFindBy;
import com.solvd.mydemoapp.mobile.dto.Product;
import com.solvd.mydemoapp.mobile.pages.common.CartPageBase;
import com.solvd.mydemoapp.mobile.pages.common.CatalogPageBase;
import com.solvd.mydemoapp.mobile.pages.common.ProductDetailsPageBase;
import org.openqa.selenium.WebDriver;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductDetailsPageBase.class)
public class ProductDetailsPage extends ProductDetailsPageBase {

    @ExtendedFindBy(iosPredicate = "name == 'ProductDetails-screen'")
    private ExtendedWebElement title;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == 'ProductDetails-screen'`]/**/XCUIElementTypeButton")
    private ExtendedWebElement productsButton;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeScrollView/**/XCUIElementTypeStaticText")
    private ExtendedWebElement nameLabel;
    @ExtendedFindBy(iosPredicate = "name == 'Price' AND type == 'XCUIElementTypeStaticText'")
    private ExtendedWebElement priceLabel;
    @ExtendedFindBy(iosPredicate = "name == 'SubtractMinus Icons' AND type == 'XCUIElementTypeButton'")
    private ExtendedWebElement minusQuantityButton;
    @ExtendedFindBy(iosPredicate = "name == 'AddPlus Icons' AND type == 'XCUIname == 'ProductsElementTypeButton'")
    private ExtendedWebElement plusQuantityButton;
    @ExtendedFindBy(iosPredicate = "name == 'Amount'")
    private ExtendedWebElement amountLabel;
    @ExtendedFindBy(iosPredicate = "name == 'Add To Cart'")
    private ExtendedWebElement addToCartButton;
    @ExtendedFindBy(iosPredicate = "name CONTAINS 'Color' AND value == '1'")
    private ExtendedWebElement selectedColor;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeButton[`name CONTAINS 'Star'`]")
    private List<ExtendedWebElement> starButtons;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeButton[`name == 'Cart-tab-item'`]")
    private ExtendedWebElement cartButton;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[-1]/XCUIElementTypeStaticText")
    private ExtendedWebElement cartAmountLabel;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isProductOpened(String productName) {
        return nameLabel.getText().equals(productName);
    }

    @Override
    public Product fetchProductDetails() {
        Product product = new Product();
        product.setName(nameLabel.getText());
        product.setPrice(getPrice());
        product.setQuantity(Integer.parseInt(amountLabel.getText()));
        product.setColor(getColor());
        product.setRate(getRate());
        return product;
    }

    @Override
    public ProductDetailsPageBase clickAddToCartButton() {
        addToCartButton.click();
        return initPage(getDriver(), ProductDetailsPageBase.class);
    }

    @Override
    public CatalogPageBase clickBackButton() {
        productsButton.click();
        return initPage(getDriver(), CatalogPageBase.class);
    }

    private int getRate() {
        int rate = 0;
        for (ExtendedWebElement starButton : starButtons) {
            if (starButton.getText().contains("StarSelected")) {
                rate++;
            }
        }
        return rate;
    }

    private String getColor() {
        return selectedColor.getAttribute("name").split("Color")[0];
    }

    private double getPrice() {
        return Double.valueOf(priceLabel.getText().replace("$", "").trim());
    }

    @Override
    public boolean isPageOpened() {
        return title.isElementPresent();
    }

    @Override
    public CartPageBase openCart() {
        cartButton.click();
        return initPage(getDriver(), CartPageBase.class);
    }

    @Override
    public int getProductsAmountInCart() {
        return Integer.valueOf(cartAmountLabel.getAttribute("name"));
    }
}
