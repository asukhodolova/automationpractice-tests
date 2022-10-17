package com.solvd.automationpractice.gui.components;

import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductList extends AbstractUIObject {

    @FindBy(xpath = "//*[contains(@class,'product_list')]/li")
    private List<ProductItem> productItems;

    public ProductList(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public List<ProductItem> getProductItems() {
        return productItems;
    }

    public ProductItem findProductByName(String productName) {
        for (int i = 0; i < productItems.size(); i++) {
            if (productItems.get(i).getProductName().equalsIgnoreCase(productName)) {
                return productItems.get(i);
            }
        }
        throw new RuntimeException(String.format("Product with name %s not found", productName));
    }
}
