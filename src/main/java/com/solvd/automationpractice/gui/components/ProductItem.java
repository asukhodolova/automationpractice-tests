package com.solvd.automationpractice.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductItem extends AbstractUIObject {

    @FindBy(xpath = ".//*[@class='product-name']")
    private ExtendedWebElement productNameLabel;

    public ProductItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getProductName() {
        return productNameLabel.getText();
    }
}
