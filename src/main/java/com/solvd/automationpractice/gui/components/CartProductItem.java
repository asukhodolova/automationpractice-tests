package com.solvd.automationpractice.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.solvd.automationpractice.dto.Product;
import com.solvd.automationpractice.dto.ProductSize;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CartProductItem extends AbstractUIObject {

    @FindBy(xpath = ".//*[@class='product-name']/a")
    private ExtendedWebElement productNameLabel;
    @FindBy(xpath = ".//*[@class='product-atributes']")
    private ExtendedWebElement productAttributesLabel;
    @FindBy(xpath = ".//*[contains(@class,'price')]")
    private ExtendedWebElement productPriceLabel;

    public CartProductItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getProductName() {
        return productNameLabel.getAttribute("title");
    }

    public String getProductPrice() {
        return productPriceLabel.getText().trim();
    }

    public String getProductColor() {
        return productAttributesLabel.getText().split(",")[0].toLowerCase();
    }

    public String getProductSize() {
        return productAttributesLabel.getText().split(",")[1].trim();
    }

    public Product fetchProductDetails() {
        Product product = new Product();
        product.setName(getProductName());
        product.setPrice(getProductPrice());
        product.setColor(getProductColor());
        product.setSize(ProductSize.valueOf(getProductSize()));
        return product;
    }
}
