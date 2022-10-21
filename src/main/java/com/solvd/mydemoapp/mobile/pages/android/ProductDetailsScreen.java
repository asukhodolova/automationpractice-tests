package com.solvd.mydemoapp.mobile.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.mydemoapp.mobile.dto.Product;
import com.solvd.mydemoapp.mobile.pages.common.CatalogScreenBase;
import com.solvd.mydemoapp.mobile.pages.common.NavigationMenuBase;
import com.solvd.mydemoapp.mobile.pages.common.ProductDetailsScreenBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductDetailsScreenBase.class)
public class ProductDetailsScreen extends ProductDetailsScreenBase implements IMobileUtils {

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

    @FindBy(xpath = "//*[contains(@content-desc,'add product to cart')]")
    private ExtendedWebElement addToCartButton;

    @FindBy(xpath = "//*[contains(@resource-id,'around')]/following-sibling::*[contains(@resource-id,'color')]")
    private ExtendedWebElement selectedColor;

    @FindBy(xpath = "//*[contains(@resource-id,'start')]")
    private List<ExtendedWebElement> starButtons;

    public ProductDetailsScreen(WebDriver driver) {
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
        product.setColor(getColor());
        swipe(amountLabel);
        product.setQuantity(Integer.parseInt(amountLabel.getText()));
        //product.setRate(getRate()); //FIXME stars with rating don't change their states
        return product;
    }

    @Override
    public ProductDetailsScreenBase clickAddToCartButton() {
        addToCartButton.click();
        return initPage(ProductDetailsScreenBase.class);
    }

    @Override
    public CatalogScreenBase clickBackButton() {
        navigateBack();
        return initPage(CatalogScreenBase.class);
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

    @Override
    public boolean isOpened() {
        return nameLabel.isElementPresent();
    }

    @Override
    public NavigationMenuBase getNavigation() {
        return initPage(NavigationMenuBase.class);
    }
}
