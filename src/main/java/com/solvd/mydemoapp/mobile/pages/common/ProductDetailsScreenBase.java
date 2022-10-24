package com.solvd.mydemoapp.mobile.pages.common;

import com.solvd.mydemoapp.mobile.dto.Product;
import org.openqa.selenium.WebDriver;

public abstract class ProductDetailsScreenBase extends AbstractScreenBase {

    public ProductDetailsScreenBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isProductOpened(String productName);

    public abstract Product fetchProductDetails();

    public abstract ProductDetailsScreenBase clickAddToCartButton();

    public abstract CatalogScreenBase clickBackButton();

}
