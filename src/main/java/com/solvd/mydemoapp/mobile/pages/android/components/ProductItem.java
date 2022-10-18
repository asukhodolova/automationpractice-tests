package com.solvd.mydemoapp.mobile.pages.android.components;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.mydemoapp.mobile.dto.Product;
import com.solvd.mydemoapp.mobile.pages.common.components.ProductItemBase;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductItemBase.class)
public class ProductItem extends ProductItemBase {

    @FindBy(xpath = ".//*[contains(@resource-id,'title')]")
    private ExtendedWebElement productNameLabel;
    @FindBy(xpath = ".//*[contains(@resource-id,'price')]")
    private ExtendedWebElement productPriceLabel;
    @FindBy(xpath = ".//*[contains(@resource-id,'ratting')]/*[contains(@resource-id,'start')]")
    private List<ExtendedWebElement> productStarButtons;

    public ProductItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public Product fetchProductDetails() {
        Product product = new Product();
        product.setName(getProductName());
        product.setPrice(getProductPrice());
        return product;
    }

    @Override
    public String getProductName() {
        return productNameLabel.getText();
    }

    @Override
    public double getProductPrice() {
        return Double.valueOf(productPriceLabel.getText().replace("$", "").trim());
    }
}
