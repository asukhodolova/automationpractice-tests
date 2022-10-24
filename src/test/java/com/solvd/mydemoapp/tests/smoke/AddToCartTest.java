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

public class AddToCartTest implements IAbstractTest {

    private Product firstProduct;
    private Product secondProduct;

    @Test(description = "Add products to cart and verify cart counter")
    @MethodOwner(owner = "asukhodolova")
    @TestPriority(Priority.P0)
    @TestLabel(name = "feature", value = {"mobile", "images", "android", "smoke"})
    public void testAddToCartAndVerifyCounter() {
        CatalogScreenBase catalogScreenBase = initPage(getDriver(), CatalogScreenBase.class);
        Assert.assertTrue(catalogScreenBase.isOpened(), "Catalog page is not opened");

        List<String> products = catalogScreenBase.getAllProductNames();

        firstProduct = openProductByNameAndGetDetails(products.get(0));
        Assert.assertEquals(initPage(getDriver(), ProductDetailsScreenBase.class).clickAddToCartButton()
                .getNavigation().getProductsAmountInCart(), firstProduct.getQuantity(), "Incorrect amount of products in the cart");

        catalogScreenBase = initPage(getDriver(), ProductDetailsScreenBase.class).clickBackButton();
        Assert.assertTrue(catalogScreenBase.isOpened(), "Catalog page is not opened");

        secondProduct = openProductByNameAndGetDetails(products.get(3));

        Assert.assertEquals(initPage(getDriver(), ProductDetailsScreenBase.class).clickAddToCartButton()
                        .getNavigation().getProductsAmountInCart(), firstProduct.getQuantity() + secondProduct.getQuantity(),
                "Incorrect amount of products in the cart");
    }

    @Test(description = "Verify cart with product details, total price and quantity", dependsOnMethods = "testAddToCartAndVerifyCounter")
    @MethodOwner(owner = "asukhodolova")
    @TestPriority(Priority.P0)
    @TestLabel(name = "feature", value = {"mobile", "images", "android", "smoke"})
    public void testVerifyCartDetails() {
        CartScreenBase cartScreenBase = initPage(getDriver(), ProductDetailsScreenBase.class).getNavigation().openCart();
        Assert.assertTrue(cartScreenBase.isOpened(), "Cart page is not opened");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartScreenBase.getAddedProductNames(),
                Arrays.asList(firstProduct.getName(), secondProduct.getName()), "Added products are incorrect");
        softAssert.assertEquals(cartScreenBase.getTotalQuantity(),
                firstProduct.getQuantity() + secondProduct.getQuantity(), "Total quantity is incorrect");
        softAssert.assertEquals(cartScreenBase.getTotalPrice(),
                firstProduct.getPrice() + secondProduct.getPrice(), "Total price is incorrect");
        softAssert.assertAll();
    }

    private Product openProductByNameAndGetDetails(String productToOpen) {
        ProductDetailsScreenBase productDetailsPageBase = initPage(getDriver(), CatalogScreenBase.class).openProductByName(productToOpen);
        Assert.assertTrue(productDetailsPageBase.isProductOpened(productToOpen), "Product " + productToOpen + " is not opened");

        return productDetailsPageBase.fetchProductDetails();
    }
}
