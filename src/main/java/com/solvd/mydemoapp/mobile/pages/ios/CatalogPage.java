package com.solvd.mydemoapp.mobile.pages.ios;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.locator.ExtendedFindBy;
import com.solvd.mydemoapp.mobile.dto.Product;
import com.solvd.mydemoapp.mobile.pages.common.CatalogPageBase;
import com.solvd.mydemoapp.mobile.pages.common.SortingPageBase;
import com.solvd.mydemoapp.mobile.pages.ios.components.ProductItem;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CatalogPageBase.class)
public class CatalogPage extends CatalogPageBase {

    @ExtendedFindBy(iosPredicate = "name = 'Catalog-screen'")
    private ExtendedWebElement title;
    @ExtendedFindBy(iosPredicate = "name == 'Button' AND type == 'XCUIElementTypeButton'")
    private ExtendedWebElement sortingButton;
    @ExtendedFindBy(iosPredicate = "name == 'ProductItem'")
    private List<ProductItem> productItems;

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SortingPageBase openSortingPage() {
        sortingButton.click();
        return initPage(getDriver(), SortingPageBase.class);
    }

    @Override
    public List<Product> getProducts() {
        return productItems.stream().map(p -> p.fetchProductDetails()).collect(Collectors.toList());
    }

    @Override
    public boolean isPageOpened() {
        return title.isElementPresent();
    }
}
