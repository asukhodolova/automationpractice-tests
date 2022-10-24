package com.solvd.mydemoapp.tests.smoke;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.solvd.mydemoapp.mobile.pages.common.CatalogScreenBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ImageLocatorTest implements IAbstractTest {

    @Test(description = "Find my image locator")
    @MethodOwner(owner = "asukhodolova")
    public void testFindByImage() {
        CatalogScreenBase catalogScreenBase = initPage(getDriver(), CatalogScreenBase.class);
        Assert.assertTrue(catalogScreenBase.isOpened(), "Catalog page is not opened");

        Assert.assertTrue(catalogScreenBase.clickOnCartByImage().isOpened(), "Cart page is not opened");
    }
}
