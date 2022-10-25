package com.solvd.automationpractice.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class ShoppingCart extends AbstractUIObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(xpath = "//*[@class='cart_block block exclusive']")
    private ExtendedWebElement shoppingCart;
    @FindBy(className = "ajax_cart_quantity")
    private ExtendedWebElement cartQuantityLabel;
    @FindBy(className = "ajax_cart_total")
    private ExtendedWebElement cartTotalPriceLabel;
    @FindBy(xpath = "//*[@class='products']/dt[contains(@class,'item')]")
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
        if (!shoppingCart.getAttribute("style").contains("display: block")) {
            cartQuantityLabel.scrollTo();
            cartQuantityLabel.hover();
        } else {
            LOGGER.info("Cart is already expanded");
        }
        return this;
    }

    public List<CartProductItem> getCartProducts() {
        return productItems;
    }
}
