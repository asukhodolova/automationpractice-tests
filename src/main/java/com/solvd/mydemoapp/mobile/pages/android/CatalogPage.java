package com.solvd.mydemoapp.mobile.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.mydemoapp.mobile.dto.Product;
import com.solvd.mydemoapp.mobile.pages.android.components.ProductItem;
import com.solvd.mydemoapp.mobile.pages.common.CatalogPageBase;
import com.solvd.mydemoapp.mobile.pages.common.SortingPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CatalogPageBase.class)
public class CatalogPage extends CatalogPageBase {
    @FindBy(xpath = "//*[contains(@resource-id,'mTvTitle')]")
    private ExtendedWebElement title;
    @FindBy(xpath = "//*[contains(@resource-id,'sort')]")
    private ExtendedWebElement sortingButton;
    @FindBy(xpath = "//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup")
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
