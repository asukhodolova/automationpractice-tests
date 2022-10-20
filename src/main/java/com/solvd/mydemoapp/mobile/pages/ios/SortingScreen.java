package com.solvd.mydemoapp.mobile.pages.ios;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.locator.ExtendedFindBy;
import com.solvd.mydemoapp.mobile.dto.Sorting;
import com.solvd.mydemoapp.mobile.pages.common.CatalogScreenBase;
import com.solvd.mydemoapp.mobile.pages.common.SortingScreenBase;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = SortingScreenBase.class)
public class SortingScreen extends SortingScreenBase {

    @ExtendedFindBy(iosPredicate = "name == 'Name - Ascending'")
    private ExtendedWebElement nameAscButton;

    @ExtendedFindBy(iosPredicate = "name == 'Name - Descending'")
    private ExtendedWebElement nameDescButton;

    @ExtendedFindBy(iosPredicate = "name == 'Price - Ascending'")
    private ExtendedWebElement priceAscButton;

    @ExtendedFindBy(iosPredicate = "name == 'Price - Descending'")
    private ExtendedWebElement priceDescButton;


    public SortingScreen(WebDriver driver) {
        super(driver);
    }

    @Override
    public CatalogScreenBase selectSortingBy(Sorting sorting) {
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
        return initPage(getDriver(), CatalogScreenBase.class);
    }

    @Override
    public boolean isOpened() {
        return nameAscButton.isElementPresent();
    }
}
