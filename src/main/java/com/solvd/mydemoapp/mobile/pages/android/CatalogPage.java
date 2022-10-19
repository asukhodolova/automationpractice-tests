package com.solvd.mydemoapp.mobile.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.mydemoapp.mobile.dto.Product;
import com.solvd.mydemoapp.mobile.pages.common.CatalogPageBase;
import com.solvd.mydemoapp.mobile.pages.common.SortingPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CatalogPageBase.class)
public class CatalogPage extends CatalogPageBase {

    private static final String PRODUCT_NAME_LABEL_LOCATOR = ".//*[contains(@resource-id,'title')]";
    private static final String PRODUCT_PRICE_LABEL_LOCATOR = ".//*[contains(@resource-id,'price')]";
    private static final String PRODUCT_STAR_BUTTON_LOCATOR = ".//*[contains(@resource-id,'ratting')]//*[contains(@resource-id,'start')]";

    @FindBy(xpath = "//*[contains(@resource-id,'mTvTitle')]")
    private ExtendedWebElement title;
    @FindBy(xpath = "//*[contains(@resource-id,'sort')]")
    private ExtendedWebElement sortingButton;
    @FindBy(xpath = "//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup")
    private List<ExtendedWebElement> productItems;

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SortingPageBase openSortingPage() {
        sortingButton.click();
        return initPage(getDriver(), SortingPageBase.class);
    }

    @Override
    public List<Product> getAllProductDetails() {
        List<Product> products = new ArrayList<>();
        for (int index = 0; index < productItems.size(); index++) {
            products.add(fetchProductDetails(index));

        }
        return products;
    }

    @Override
    public boolean isPageOpened() {
        return title.isElementPresent();
    }

    @Override
    public Product fetchProductDetails(int productIndex) {
        Product product = new Product();
        product.setName(getProductName(productIndex));
        product.setPrice(getProductPrice(productIndex));
        return product;
    }

    private String getProductName(int productIndex) {
        return productItems.get(productIndex).findExtendedWebElement(By.xpath(PRODUCT_NAME_LABEL_LOCATOR)).getText();
    }

    private double getProductPrice(int productIndex) {
        return Double.valueOf(productItems.get(productIndex).findExtendedWebElement(By.xpath(PRODUCT_PRICE_LABEL_LOCATOR))
                .getText().replace("$", "").trim());
    }
}
