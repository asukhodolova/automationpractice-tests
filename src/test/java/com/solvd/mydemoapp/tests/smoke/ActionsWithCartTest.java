package com.solvd.mydemoapp.tests.smoke;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.core.foundation.utils.tag.Priority;
import com.qaprosoft.carina.core.foundation.utils.tag.TestPriority;
import com.solvd.mydemoapp.mobile.dto.Product;
import com.solvd.mydemoapp.mobile.pages.common.CartPageBase;
import com.solvd.mydemoapp.mobile.pages.common.CatalogPageBase;
import com.solvd.mydemoapp.mobile.pages.common.ProductDetailsPageBase;
import com.zebrunner.agent.core.annotation.TestLabel;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class ActionsWithCartTest implements IAbstractTest, IMobileUtils {

    private Product firstProduct;
    private Product secondProduct;

    @Test(description = "Add products to cart and verify cart counter")
    @MethodOwner(owner = "asukhodolova")
    @TestPriority(Priority.P0)
    @TestLabel(name = "feature", value = {"mobile", "ios", "android", "smoke"})
    public void testAddToCartAndVerifyCounter() {
        CatalogPageBase catalogPageBase = initPage(getDriver(), CatalogPageBase.class);
        Assert.assertTrue(catalogPageBase.isPageOpened(), "Catalog page is not opened");

        List<String> products = catalogPageBase.getAllProductNames();

        firstProduct = openProductByNameAndGetDetails(products.get(0));
        Assert.assertEquals(initPage(getDriver(), ProductDetailsPageBase.class).clickAddToCartButton().getProductsAmountInCart(),
                firstProduct.getQuantity(), "Incorrect amount of products in the cart");

        catalogPageBase = initPage(getDriver(), ProductDetailsPageBase.class).clickBackButton();
        Assert.assertTrue(catalogPageBase.isPageOpened(), "Catalog page is not opened");

        secondProduct = openProductByNameAndGetDetails(products.get(3));

        Assert.assertEquals(initPage(getDriver(), ProductDetailsPageBase.class).clickAddToCartButton().getProductsAmountInCart(),
                firstProduct.getQuantity() + secondProduct.getQuantity(),
                "Incorrect amount of products in the cart");
    }

    @Test(description = "Verify cart with product details, total price and quantity", dependsOnMethods = "testAddToCartAndVerifyCounter")
    @MethodOwner(owner = "asukhodolova")
    @TestPriority(Priority.P0)
    @TestLabel(name = "feature", value = {"mobile", "ios", "android", "smoke"})
    public void testVerifyCartDetails() {
        CartPageBase cartPageBase = initPage(getDriver(), ProductDetailsPageBase.class).openCart();
        Assert.assertTrue(cartPageBase.isPageOpened(), "Cart page is not opened");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPageBase.getAddedProductNames(),
                Arrays.asList(firstProduct.getName(), secondProduct.getName()), "Added products are incorrect");
        softAssert.assertEquals(cartPageBase.getTotalQuantity(),
                firstProduct.getQuantity() + secondProduct.getQuantity(), "Total quantity is incorrect");
        softAssert.assertEquals(cartPageBase.getTotalPrice(),
                firstProduct.getPrice() + secondProduct.getPrice(), "Total price is incorrect");
        softAssert.assertAll();
    }

    private Product openProductByNameAndGetDetails(String productToOpen) {
        ProductDetailsPageBase productDetailsPageBase = initPage(getDriver(), CatalogPageBase.class).openProductByName(productToOpen);
        Assert.assertTrue(productDetailsPageBase.isProductOpened(productToOpen), "Product " + productToOpen + " is not opened");

        return productDetailsPageBase.fetchProductDetails();
    }
}
