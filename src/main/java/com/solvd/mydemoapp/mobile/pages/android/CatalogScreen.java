package com.solvd.mydemoapp.mobile.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.mydemoapp.mobile.dto.Product;
import com.solvd.mydemoapp.mobile.pages.common.CatalogScreenBase;
import com.solvd.mydemoapp.mobile.pages.common.INavigationMenu;
import com.solvd.mydemoapp.mobile.pages.common.ProductDetailsScreenBase;
import com.solvd.mydemoapp.mobile.pages.common.SortingScreenBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CatalogScreenBase.class)
public class CatalogScreen extends CatalogScreenBase {

    @FindBy(xpath = "//*[contains(@resource-id,'mTvTitle')]")
    private ExtendedWebElement title;

    @FindBy(xpath = "//*[contains(@resource-id,'sort')]")
    private ExtendedWebElement sortingButton;

    @FindBy(xpath = "//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup")
    private List<ExtendedWebElement> productItems;

    @FindBy(xpath = "//*[contains(@resource-id,'title')]")
    private List<ExtendedWebElement> productNameLabels;

    @FindBy(xpath = "//*[contains(@resource-id,'price')]")
    private List<ExtendedWebElement> productPriceLabels;

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
        for (int index = 0; index < productItems.size(); index++) {
            products.add(fetchProductDetails(index));

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

    @Override
    public List<String> getAllProductNames() {
        return productNameLabels.stream().map(p -> p.getText()).collect(Collectors.toList());
    }

    @Override
    public ProductDetailsScreenBase openProductByName(String productName) {
        for (int i = 0; i < productNameLabels.size(); i++) {
            if (productNameLabels.get(i).getText().equals(productName)) {
                productItems.get(i).click();
                return initPage(getDriver(), ProductDetailsScreenBase.class);
            }
        }
        throw new RuntimeException("Product " + productName + " not found");
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
