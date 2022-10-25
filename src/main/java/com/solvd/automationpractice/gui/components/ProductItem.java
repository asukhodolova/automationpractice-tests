package com.solvd.automationpractice.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.solvd.automationpractice.dto.Product;
import com.solvd.automationpractice.gui.pages.ShoppingCartWindow;
import com.solvd.automationpractice.utils.StringUtils;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductItem extends AbstractUIObject {

    @FindBy(xpath = ".//*[@class='product-name']")
    private ExtendedWebElement productNameLabel;
    @FindBy(xpath = ".//*[contains(@class,'product-price')]")
    private ExtendedWebElement productPriceLabel;
    @FindBy(xpath = ".//*[contains(@class,'color_to_pick_list')]//a")
    private List<ExtendedWebElement> productColors;
    @FindBy(xpath = ".")
    private ExtendedWebElement productItem;
    @FindBy(xpath = ".//a[contains(@class,'add_to_cart')]")
    private ExtendedWebElement addToCartButton;

    public ProductItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getProductName() {
        return productNameLabel.getText().trim();
    }

    public String getProductPrice() {
        return productPriceLabel.getText().trim();
    }

    public String getDefaultProductColor() {
        // it seems color is selected according to the smallest id by default
        Integer number = null;
        String hrefAttr = null;
        for (ExtendedWebElement color : productColors) {
            int id = Integer.valueOf(StringUtils.getNumbersFromString(color.getAttribute("id")));
            if (number == null) {
                number = id;
                hrefAttr = color.getAttribute("href");
            }
            if (number != null && number > id) {
                number = id;
                hrefAttr = color.getAttribute("href");
            }
        }
        return hrefAttr.split("/")[hrefAttr.split("/").length - 1].split("-")[1];
    }

    public ShoppingCartWindow addToCart() {
        productItem.scrollTo();
        productItem.hover();
        addToCartButton.click();
        return new ShoppingCartWindow(driver);
    }

    public Product fetchProductDetails() {
        productItem.scrollTo();
        productItem.hover();

        Product product = new Product();
        product.setName(getProductName());
        product.setPrice(getProductPrice());
        product.setColor(getDefaultProductColor());
        return product;
    }
}
