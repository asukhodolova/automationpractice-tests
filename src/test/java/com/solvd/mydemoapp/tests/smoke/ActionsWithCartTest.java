package com.solvd.mydemoapp.tests.smoke;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.core.foundation.utils.tag.Priority;
import com.qaprosoft.carina.core.foundation.utils.tag.TestPriority;
import com.solvd.mydemoapp.mobile.dto.Product;
import com.solvd.mydemoapp.mobile.pages.common.CartScreenBase;
import com.solvd.mydemoapp.mobile.pages.common.CatalogScreenBase;
import com.solvd.mydemoapp.mobile.pages.common.ProductDetailsScreenBase;
import com.zebrunner.agent.core.annotation.TestLabel;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class ActionsWithCartTest implements IAbstractTest {

    private static final int INCREASE_COUNT = 2;
    private static final int DECREASE_COUNT = 1;
    private static final int ZERO_COUNT = 0;
    private Product product;

    @Test(description = "Add product to cart and verify cart details", groups = "basic")
    @MethodOwner(owner = "asukhodolova")
    @TestPriority(Priority.P0)
    @TestLabel(name = "feature", value = {"mobile", "ios", "android", "smoke"})
    public void testAddToCartAndVerifyCartDetails() {
        CatalogScreenBase catalogScreenBase = initPage(getDriver(), CatalogScreenBase.class);
        Assert.assertTrue(catalogScreenBase.isOpened(), "Catalog page is not opened");

        List<String> products = catalogScreenBase.getAllProductNames();
        String productToOpen = products.get(0);

        ProductDetailsScreenBase productDetailsPageBase = catalogScreenBase.openProductByName(productToOpen);
        Assert.assertTrue(productDetailsPageBase.isProductOpened(productToOpen), "Product " + productToOpen + " is not opened");

        product = productDetailsPageBase.clickAddToCartButton().fetchProductDetails();
        openCartIfNecessary();
        verifyCartDetails(product);
    }

    @Test(alwaysRun = true, description = "Increase quantity of product and verify updated cart details", dependsOnMethods = "testAddToCartAndVerifyCartDetails")
    @MethodOwner(owner = "asukhodolova")
    @TestPriority(Priority.P0)
    @TestLabel(name = "feature", value = {"mobile", "ios", "android", "smoke"})
    public void testIncreaseProductQuantityAndVerifyUpdatedCartDetails() {
        openCartIfNecessary();

        initPage(getDriver(), CartScreenBase.class).increaseProductQuantity(product.getName(), INCREASE_COUNT);
        product.setQuantity(product.getQuantity() + INCREASE_COUNT);

        verifyCartDetails(product);
    }

    @Test(alwaysRun = true, description = "Decrease quantity of product and verify updated cart details", dependsOnMethods = "testIncreaseProductQuantityAndVerifyUpdatedCartDetails")
    @MethodOwner(owner = "asukhodolova")
    @TestPriority(Priority.P0)
    @TestLabel(name = "feature", value = {"mobile", "ios", "android", "smoke"})
    public void testDecreaseProductQuantityAndVerifyUpdatedCartDetails() {
        openCartIfNecessary();

        initPage(getDriver(), CartScreenBase.class).decreaseProductQuantity(product.getName(), DECREASE_COUNT);
        product.setQuantity(product.getQuantity() - DECREASE_COUNT);

        verifyCartDetails(product);
    }

    @Test(alwaysRun = true, description = "Remove items and verify empty cart", dependsOnMethods = "testDecreaseProductQuantityAndVerifyUpdatedCartDetails")
    @MethodOwner(owner = "asukhodolova")
    @TestPriority(Priority.P0)
    @TestLabel(name = "feature", value = {"mobile", "ios", "android", "smoke"})
    public void testRemoveItemsAndVerifyEmptyCart() {
        openCartIfNecessary();

        initPage(getDriver(), CartScreenBase.class).removeProduct(product.getName());
        product.setQuantity(ZERO_COUNT);

        verifyCartDetails(product);

        CatalogScreenBase catalogScreenBase = initPage(getDriver(), CartScreenBase.class).clickGoShoppingButton();
        Assert.assertTrue(catalogScreenBase.isOpened(), "Catalog page is not opened");
    }

    private void openCartIfNecessary() {
        CartScreenBase cartScreenBase = initPage(getDriver(), CartScreenBase.class);
        if (!cartScreenBase.isOpened()) {
            cartScreenBase.getNavigation().openCart();
        }
        Assert.assertTrue(cartScreenBase.isOpened(), "Cart page is not opened");
    }

    private void verifyCartDetails(Product product) {
        CartScreenBase cartScreenBase = initPage(getDriver(), CartScreenBase.class);

        SoftAssert softAssert = new SoftAssert();
        if (product.getQuantity() == 0) {
            softAssert.assertTrue(cartScreenBase.getNavigation().isNoCartCounter(), "Cart counter != 0");
            softAssert.assertTrue(cartScreenBase.isCartEmpty(), "Cart is not empty");
        } else {
            softAssert.assertEquals(cartScreenBase.getNavigation().getProductsAmountInCart(), product.getQuantity(), "Cart quantity is incorrect");
            softAssert.assertEquals(cartScreenBase.getAddedProductNames(), Arrays.asList(product.getName()), "Added products are incorrect");
            softAssert.assertEquals(cartScreenBase.getTotalQuantity(), product.getQuantity(), "Total quantity is incorrect");
            softAssert.assertEquals(cartScreenBase.getTotalPrice(), product.getPrice() * product.getQuantity(), "Total price is incorrect");
        }
        softAssert.assertAll();
    }
}
