package com.solvd.mydemoapp.mobile.pages.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.mydemoapp.mobile.dto.Product;
import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class CatalogScreenBase extends AbstractPage implements ICommonScreen {

    public CatalogScreenBase(WebDriver driver) {
        super(driver);
    }

    public abstract SortingScreenBase openSortingPage();

    public abstract List<Product> getAllProductDetails();

    public abstract Product fetchProductDetails(int productIndex);

    public abstract List<String> getAllProductNames();

    public abstract ProductDetailsScreenBase openProductByName(String productName);
}
