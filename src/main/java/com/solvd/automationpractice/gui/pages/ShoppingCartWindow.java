package com.solvd.automationpractice.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartWindow extends AbstractPage {

    private static final String ADDED_TO_CART_SUCCESS_MESSAGE = "Product successfully added to your shopping cart";
    private static final String NUMBER_OF_ITEMS_IN_CART_MESSAGE_PATTERN = "There %s %d item%s in your cart.";
    @FindBy(id = "layer_cart")
    private ExtendedWebElement layerCartBlock;
    @FindBy(xpath = "//*[@title='Close window']")
    private ExtendedWebElement closeButton;
    @FindBy(xpath = "//*[@title='Continue shopping']")
    private ExtendedWebElement continueShoppingButton;
    @FindBy(xpath = "//*[@title='Proceed to checkout']")
    private ExtendedWebElement proceedToCheckoutButton;
    @FindBy(xpath = "//*[@id='layer_cart']//h2")
    private ExtendedWebElement statusTextLabel;
    @FindBy(xpath = "//*[@id='layer_cart']//*[contains(@class,'ajax_cart_product') and not(@style='display: none;')]")
    private ExtendedWebElement numberOfItemsInCartLabel;
    @FindBy(id = "layer_cart_product_title")
    private ExtendedWebElement productNameLabel;
    @FindBy(id = "layer_cart_product_attributes")
    private ExtendedWebElement productAttributesLabel;
    @FindBy(id = "layer_cart_product_quantity")
    private ExtendedWebElement productQuantityLabel;
    @FindBy(id = "layer_cart_product_price")
    private ExtendedWebElement productPriceLabel;

    public ShoppingCartWindow(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(layerCartBlock);
    }

    public boolean isAddToCartSuccessMessagePresent() {
        return statusTextLabel.getText().trim().equals(ADDED_TO_CART_SUCCESS_MESSAGE);
    }

    public boolean isNumberOfItemsInCartCorrect(int numberOfItems) {
        String message = numberOfItems == 1
                ? String.format(NUMBER_OF_ITEMS_IN_CART_MESSAGE_PATTERN, "is", numberOfItems, "")
                : String.format(NUMBER_OF_ITEMS_IN_CART_MESSAGE_PATTERN, "are", numberOfItems, "s");
        return numberOfItemsInCartLabel.getText().trim().equals(message);
    }

    public String getProductName() {
        return productNameLabel.getText();
    }

    public String getProductColor() {
        return productAttributesLabel.getText().split(",")[0].toLowerCase();
    }

    public String getProductSize() {
        return productAttributesLabel.getText().split(",")[1].trim();
    }

    public int getProductQuantity() {
        return Integer.parseInt(productQuantityLabel.getText());
    }

    public String getProductPrice() {
        return productPriceLabel.getText();
    }

    public void clickContinueShopping() {
        continueShoppingButton.click();
    }

    public void close() {
        closeButton.click();
    }
}
