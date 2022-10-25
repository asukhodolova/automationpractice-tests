package com.solvd.automationpractice.tests.smoke;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.core.foundation.utils.tag.Priority;
import com.qaprosoft.carina.core.foundation.utils.tag.TestPriority;
import com.qaprosoft.carina.core.foundation.webdriver.Screenshot;
import com.solvd.automationpractice.dto.Product;
import com.solvd.automationpractice.gui.components.ProductItem;
import com.solvd.automationpractice.gui.components.ShoppingCart;
import com.solvd.automationpractice.gui.pages.HomePage;
import com.solvd.automationpractice.gui.pages.ShoppingCartWindow;
import com.solvd.automationpractice.gui.pages.WomenPage;
import com.zebrunner.agent.core.annotation.TestLabel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddToCartTest implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private int cartQuantity = 0;
    private Product firstProduct;
    private Product secondProduct;

    @BeforeSuite
    public void startDriver() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
    }

    @Test(description = "Add to cart and verify shopping cart window details")
    @MethodOwner(owner = "asukhodolova")
    @TestPriority(Priority.P0)
    @TestLabel(name = "feature", value = {"web", "smoke"})
    public void testAddToCart() {
        HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");

        LOGGER.debug("Opening Women tab");
        WomenPage womenPage = homePage.getHeader().getMenu().openWomenTab();
        Assert.assertTrue(womenPage.isPageOpened(), "Women page is not opened");

        LOGGER.debug("Fetching available products");
        List<ProductItem> availableProducts = womenPage.getProducts();
        Assert.assertTrue(availableProducts.size() > 0, "No products on the page");

        List<String> availableProductNames = availableProducts.stream().map(p -> p.getProductName()).collect(Collectors.toList());

        LOGGER.debug("Fetching details of first product");
        firstProduct = womenPage.getProductList().findProductByName(availableProductNames.get(0)).fetchProductDetails();

        LOGGER.debug("Adding the product to the cart");
        womenPage.getProductList().findProductByName(firstProduct.getName()).addToCart();
        cartQuantity++;

        verifyShoppingCartWindowDetails(cartQuantity, firstProduct);
    }

    @Test(description = "Proceed shopping and verify cart details", dependsOnMethods = "testAddToCart")
    @MethodOwner(owner = "asukhodolova")
    @TestPriority(Priority.P0)
    @TestLabel(name = "feature", value = {"web", "smoke"})
    public void testProceedShoppingAndVerifyCartDetails() {
        new ShoppingCartWindow(getDriver()).clickContinueShoppingButton();
        verifyAddedToCartProducts(cartQuantity, Arrays.asList(firstProduct));
    }

    @Test(description = "Add to cart more products", dependsOnMethods = "testProceedShoppingAndVerifyCartDetails")
    @MethodOwner(owner = "asukhodolova")
    @TestPriority(Priority.P0)
    @TestLabel(name = "feature", value = {"web", "smoke"})
    public void testAddToCartMoreProducts() {
        WomenPage womenPage = new HomePage(getDriver()).getHeader().getMenu().openWomenTab();
        Assert.assertTrue(womenPage.isPageOpened(), "Women page is not opened");

        List<ProductItem> availableProducts = womenPage.getProducts();
        Assert.assertTrue(availableProducts.size() > 1, "Not enough products on the page");

        List<String> availableProductNames = availableProducts.stream().map(p -> p.getProductName()).collect(Collectors.toList());

        secondProduct = womenPage.getProductList().findProductByName(availableProductNames.get(1)).fetchProductDetails();

        womenPage.getProductList().findProductByName(secondProduct.getName()).addToCart();
        cartQuantity++;

        verifyShoppingCartWindowDetails(cartQuantity, secondProduct);
    }

    @Test(description = "Verify updated cart details", dependsOnMethods = "testAddToCartMoreProducts")
    @MethodOwner(owner = "asukhodolova")
    @TestPriority(Priority.P0)
    @TestLabel(name = "feature", value = {"web", "smoke"})
    public void testVerifyUpdatedCartDetails() {
        new ShoppingCartWindow(getDriver()).clickCloseButton();
        verifyAddedToCartProducts(cartQuantity, Arrays.asList(firstProduct, secondProduct));
    }

    private void verifyAddedToCartProducts(int cartQuantity, List<Product> products) {
        ShoppingCart shoppingCart = new WomenPage(getDriver()).getHeader().getShoppingCart();
        List<Product> cartProducts = shoppingCart.expandCart().getCartProducts().stream().map(p -> p.fetchProductDetails()).collect(Collectors.toList());

        Screenshot.capture(getDriver(), "", true);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(shoppingCart.getCartQuantity(), cartQuantity, "Cart quantity is incorrect");
        softAssert.assertEquals(cartProducts.size(), cartQuantity, "Cart products size is incorrect");
        softAssert.assertEquals(cartProducts, products, "Products are incorrect");
        softAssert.assertAll();
    }

    private void verifyShoppingCartWindowDetails(int cartQuantity, Product product) {
        Screenshot.capture(getDriver(), "", true);

        SoftAssert softAssert = new SoftAssert();
        ShoppingCartWindow shoppingCartWindow = new ShoppingCartWindow(getDriver());
        softAssert.assertTrue(shoppingCartWindow.isPageOpened(), "Shopping cart window is not opened");
        softAssert.assertTrue(shoppingCartWindow.isAddToCartSuccessMessagePresent(), "Success message is not present or incorrect");
        softAssert.assertTrue(shoppingCartWindow.isNumberOfItemsInCartCorrect(cartQuantity), "Incorrect number of items or message is incorrect");

        softAssert.assertEquals(shoppingCartWindow.getProductName(), product.getName(), "Added product name is incorrect");
        softAssert.assertEquals(shoppingCartWindow.getProductPrice(), product.getPrice(), "Added product price is incorrect");
        softAssert.assertEquals(shoppingCartWindow.getProductQuantity(), product.getQuantity(), "Added product quantity is incorrect");
        softAssert.assertEquals(shoppingCartWindow.getProductColor(), product.getColor(), "Added product color is incorrect");
        softAssert.assertEquals(shoppingCartWindow.getProductSize(), product.getSize().toString(), "Added product size is incorrect");

        softAssert.assertAll();
    }

}
