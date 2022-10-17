package com.solvd.automationpractice.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.automationpractice.gui.components.Header;
import com.solvd.automationpractice.gui.components.ProductItem;
import com.solvd.automationpractice.gui.components.ProductList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WomenPage extends AbstractPage {

    @FindBy(id = "header")
    private Header header;
    @FindBy(xpath = "//*[@class='category-name' and contains(text(),'Women')]")
    private ExtendedWebElement womenBlock;
    @FindBy(xpath = "//*[contains(@class,'product_list')]")
    private ProductList productListBlock;

    public WomenPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(womenBlock);
    }

    public Header getHeader() {
        return header;
    }

    public List<ProductItem> getProducts() {
        return productListBlock.getProductItems();
    }

    public ProductList getProductList() {
        return productListBlock;
    }
}
