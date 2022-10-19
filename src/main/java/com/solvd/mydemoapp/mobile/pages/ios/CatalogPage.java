package com.solvd.mydemoapp.mobile.pages.ios;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.locator.ExtendedFindBy;
import com.solvd.mydemoapp.mobile.dto.Product;
import com.solvd.mydemoapp.mobile.pages.common.CatalogPageBase;
import com.solvd.mydemoapp.mobile.pages.common.SortingPageBase;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CatalogPageBase.class)
public class CatalogPage extends CatalogPageBase {

    @ExtendedFindBy(iosPredicate = "name = 'Catalog-screen'")
    private ExtendedWebElement title;
    @ExtendedFindBy(iosPredicate = "name == 'Button' AND type == 'XCUIElementTypeButton'")
    private ExtendedWebElement sortingButton;
    @ExtendedFindBy(iosPredicate = "name == 'ProductItem'")
    private List<ExtendedWebElement> productItems;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == 'ProductItem'`]/XCUIElementTypeStaticText[`NOT(name CONTAINS '$')`]")
    private List<ExtendedWebElement> productNameLabels;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText[`name CONTAINS '$'`]")
    private List<ExtendedWebElement> productPriceLabels;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeButton[`name CONTAINS 'Star'`]")
    private List<ExtendedWebElement> productStarButtons;

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
        for (int i = 0; i < productItems.size(); i++) {
            products.add(fetchProductDetails(i));
        }
        return products;
    }

    @Override
    public Product fetchProductDetails(int productIndex) {
        Product product = new Product();
        product.setName(getProductName(productIndex));
        product.setPrice(getProductPrice(productIndex));
        return product;
    }

    private String getProductName(int productIndex) {
        return productNameLabels.get(productIndex).getText();
    }

    private double getProductPrice(int productIndex) {
        return Double.valueOf(productPriceLabels.get(productIndex).getText().replace("$", "").trim());
    }

    private int getProductRate(int productIndex) {
        int rate = 0;
        for (int i = productIndex; i < productIndex + 5; i++) {
            if (productStarButtons.get(i).getAttribute("name").contains("StarSelected")) {
                rate++;
            }
        }
        return rate;
    }

    @Override
    public boolean isPageOpened() {
        return title.isElementPresent();
    }
}
