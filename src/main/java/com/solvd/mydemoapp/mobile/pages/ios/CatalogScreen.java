package com.solvd.mydemoapp.mobile.pages.ios;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.locator.ExtendedFindBy;
import com.solvd.mydemoapp.mobile.dto.Product;
import com.solvd.mydemoapp.mobile.pages.common.CatalogScreenBase;
import com.solvd.mydemoapp.mobile.pages.common.INavigationMenu;
import com.solvd.mydemoapp.mobile.pages.common.ProductDetailsScreenBase;
import com.solvd.mydemoapp.mobile.pages.common.SortingScreenBase;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CatalogScreenBase.class)
public class CatalogScreen extends CatalogScreenBase {

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

    public CatalogScreen(WebDriver driver) {
        super(driver);
    }

    @Override
    public SortingScreenBase openSortingPage() {
        sortingButton.click();
        return initPage(getDriver(), SortingScreenBase.class);
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

    @Override
    public List<String> getAllProductNames() {
        return productNameLabels.stream().map(p -> p.getText()).collect(Collectors.toList());
    }

    @Override
    public ProductDetailsScreenBase openProductByName(String productName) {
        Optional<ExtendedWebElement> product = productNameLabels.stream().filter(p -> p.getText().equals(productName)).findFirst();
        if (product.isPresent()) {
            product.get().click();
            return initPage(getDriver(), ProductDetailsScreenBase.class);
        }
        throw new RuntimeException("Product " + productName + " not found");
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
    public boolean isOpened() {
        return title.isElementPresent();
    }

    @Override
    public INavigationMenu getNavigation() {
        return new NavigationMenu(getDriver());
    }
}
