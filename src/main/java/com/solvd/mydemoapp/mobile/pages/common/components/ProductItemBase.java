package com.solvd.mydemoapp.mobile.pages.common.components;

import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.solvd.mydemoapp.mobile.dto.Product;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public abstract class ProductItemBase extends AbstractUIObject {

    public ProductItemBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public abstract Product fetchProductDetails();

    public abstract String getProductName();

    public abstract double getProductPrice();
}
