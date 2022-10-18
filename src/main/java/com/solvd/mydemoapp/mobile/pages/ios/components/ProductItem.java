package com.solvd.mydemoapp.mobile.pages.ios.components;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.locator.ExtendedFindBy;
import com.solvd.mydemoapp.mobile.dto.Product;
import com.solvd.mydemoapp.mobile.pages.common.components.ProductItemBase;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductItemBase.class)
public class ProductItem extends ProductItemBase {

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText")
    private ExtendedWebElement productNameLabel;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText[`name CONTAINS '$'`]")
    private ExtendedWebElement productPriceLabel;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeButton[`name == 'StarSelected Icons'`]")
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
