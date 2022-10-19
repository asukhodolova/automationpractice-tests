package com.solvd.mydemoapp.mobile.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.mydemoapp.mobile.dto.Product;
import com.solvd.mydemoapp.mobile.pages.common.CartPageBase;
import com.solvd.mydemoapp.mobile.pages.common.CatalogPageBase;
import com.solvd.mydemoapp.mobile.pages.common.ProductDetailsPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductDetailsPageBase.class)
public class ProductDetailsPage extends ProductDetailsPageBase implements IMobileUtils {

    @FindBy(xpath = "//android.widget.TextView[contains(@resource-id,'cart')]")
    private ExtendedWebElement cartAmountLabel;
    @FindBy(xpath = "//*[@content-desc='View cart']")
    private ExtendedWebElement cartButton;
    @FindBy(xpath = "//*[contains(@resource-id,'product')]")
    private ExtendedWebElement nameLabel;
    @FindBy(xpath = "//*[contains(@resource-id,'price')]")
    private ExtendedWebElement priceLabel;
    @FindBy(xpath = "//*[contains(@resource-id,'minus')]")
    private ExtendedWebElement minusQuantityButton;
    @FindBy(xpath = "//*[contains(@resource-id,'plus')]")
    private ExtendedWebElement plusQuantityButton;
    @FindBy(xpath = "//*[contains(@resource-id,'noTV')]")
    private ExtendedWebElement amountLabel;
    @FindBy(xpath = "//*[contains(@resource-id,'cartBt')]")
    private ExtendedWebElement addToCartButton;
    @FindBy(xpath = "//*[contains(@resource-id,'around')]/following-sibling::*[contains(@resource-id,'color')]")
    private ExtendedWebElement selectedColor;
    @FindBy(xpath = "//*[contains(@resource-id,'start')]")
    private List<ExtendedWebElement> starButtons;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return nameLabel.isElementPresent();
    }

    @Override
    public CartPageBase openCart() {
        cartButton.click();
        return initPage(getDriver(), CartPageBase.class);
    }

    @Override
    public int getProductsAmountInCart() {
        return Integer.parseInt(cartAmountLabel.getText());
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
        product.setColor(getColor());
        swipe(amountLabel);
        product.setQuantity(Integer.parseInt(amountLabel.getText()));
        //product.setRate(getRate()); //FIXME
        return product;
    }

    @Override
    public ProductDetailsPageBase clickAddToCartButton() {
        addToCartButton.click();
        return initPage(getDriver(), ProductDetailsPageBase.class);
    }

    @Override
    public CatalogPageBase clickBackButton() {
        navigateBack();
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
        return selectedColor.getAttribute("content-desc").split(" ")[0];
    }

    private double getPrice() {
        return Double.valueOf(priceLabel.getText().replace("$", "").trim());
    }
}
