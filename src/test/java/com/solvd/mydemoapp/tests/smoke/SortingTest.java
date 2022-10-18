package com.solvd.mydemoapp.tests.smoke;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.core.foundation.utils.tag.Priority;
import com.qaprosoft.carina.core.foundation.utils.tag.TestPriority;
import com.solvd.automationpractice.utils.DataUtils;
import com.solvd.mydemoapp.mobile.dto.Product;
import com.solvd.mydemoapp.mobile.dto.Sorting;
import com.solvd.mydemoapp.mobile.pages.common.CatalogPageBase;
import com.zebrunner.agent.core.annotation.TestLabel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

import static com.solvd.mydemoapp.mobile.dto.Sorting.*;

public class SortingTest implements IAbstractTest, IMobileUtils {

    private static final List<Sorting> SORTS = Arrays.asList(NAME_DESC, NAME_ASC, PRICE_DESC, PRICE_ASC);

    @Test(description = "Perform sorting and verify products list", dataProvider = "sorting")
    @MethodOwner(owner = "asukhodolova")
    @TestPriority(Priority.P0)
    @TestLabel(name = "feature", value = {"mobile", "ios", "smoke"})
    public void testSorting(Sorting sorting) {
        CatalogPageBase catalogPageBase = initPage(getDriver(), CatalogPageBase.class);
        Assert.assertTrue(catalogPageBase.isPageOpened(), "Catalog page is not opened");

        List<Product> actualProducts =
                catalogPageBase.openSortingPage().selectSortingBy(sorting).getProducts().stream().map(p -> p.fetchProductDetails()).collect(Collectors.toList());
        List<Product> expectedProducts = new ArrayList<>(actualProducts);
        switch (sorting) {
            case NAME_ASC:
                Collections.sort(expectedProducts, Comparator.comparing(Product::getName));
                break;
            case NAME_DESC:
                Collections.sort(expectedProducts, Comparator.comparing(Product::getName).reversed());
                break;
            case PRICE_ASC:
                Collections.sort(expectedProducts, Comparator.comparing(Product::getPrice));
                break;
            case PRICE_DESC:
                Collections.sort(expectedProducts, Comparator.comparing(Product::getPrice).reversed());
                break;
            default:
                throw new RuntimeException("Unknown sorting type " + sorting);
        }
        Assert.assertEquals(actualProducts, expectedProducts, "Sorting for " + sorting + " is incorrect");
    }

    @DataProvider(name = "sorting")
    public static Object[][] sorting() {
        return DataUtils.listToDataProviderArray(SORTS);
    }

}
