package com.solvd.mydemoapp.mobile.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.mydemoapp.mobile.dto.Sorting;
import com.solvd.mydemoapp.mobile.pages.common.CatalogPageBase;
import com.solvd.mydemoapp.mobile.pages.common.SortingPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = SortingPageBase.class)
public class SortingPage extends SortingPageBase {

    @FindBy(xpath = "//*[contains(@resource-id,'nameAsc')]")
    private ExtendedWebElement nameAscButton;
    @FindBy(xpath = "//*[contains(@resource-id,'nameDes')]")
    private ExtendedWebElement nameDescButton;
    @FindBy(xpath = "//*[contains(@resource-id,'priceAsc')]")
    private ExtendedWebElement priceAscButton;
    @FindBy(xpath = "//*[contains(@resource-id,'priceDes')]")
    private ExtendedWebElement priceDescButton;

    public SortingPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CatalogPageBase selectSortingBy(Sorting sorting) {
        switch (sorting) {
            case NAME_ASC:
                nameAscButton.click();
                break;
            case NAME_DESC:
                nameDescButton.click();
                break;
            case PRICE_ASC:
                priceAscButton.click();
                break;
            case PRICE_DESC:
                priceDescButton.click();
                break;
            default:
                throw new RuntimeException("Unknown sorting type " + sorting);
        }
        return initPage(getDriver(), CatalogPageBase.class);
    }
}
