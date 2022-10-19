package com.solvd.mydemoapp.mobile.pages.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.mydemoapp.mobile.dto.Product;
import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class CatalogPageBase extends AbstractPage {
    public CatalogPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract SortingPageBase openSortingPage();

    public abstract List<Product> getAllProductDetails();

    public abstract Product fetchProductDetails(int productIndex);
}
