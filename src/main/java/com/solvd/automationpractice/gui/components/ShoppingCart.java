package com.solvd.automationpractice.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShoppingCart extends AbstractUIObject {

    @FindBy(xpath = "//*[@class='cart_block block exclusive']")
    private ExtendedWebElement shoppingCart;
    @FindBy(className = "ajax_cart_quantity")
    private ExtendedWebElement cartQuantityLabel;
    @FindBy(className = "ajax_cart_total")
    private ExtendedWebElement cartTotalPriceLabel;
    @FindBy(xpath = "//*[@class='products']/*[contains(@class,'item')]")
    private List<CartProductItem> productItems;

    public ShoppingCart(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public int getCartQuantity() {
        return Integer.parseInt(cartQuantityLabel.getText());
    }

    public String getCartTotalPrice() {
        return cartTotalPriceLabel.getText();
    }

    public ShoppingCart expandCart() {
        cartQuantityLabel.hover();
        return this;
    }

    public List<CartProductItem> getCartProducts() {
        return productItems;
    }
}
