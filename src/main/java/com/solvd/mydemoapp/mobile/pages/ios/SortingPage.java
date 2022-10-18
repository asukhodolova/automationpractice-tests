package com.solvd.mydemoapp.mobile.pages.ios;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.locator.ExtendedFindBy;
import com.solvd.mydemoapp.mobile.dto.Sorting;
import com.solvd.mydemoapp.mobile.pages.common.CatalogPageBase;
import com.solvd.mydemoapp.mobile.pages.common.SortingPageBase;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = SortingPageBase.class)
public class SortingPage extends SortingPageBase {

    @ExtendedFindBy(iosPredicate = "name == 'Name - Ascending'")
    private ExtendedWebElement nameAscButton;
    @ExtendedFindBy(iosPredicate = "name == 'Name - Descending'")
    private ExtendedWebElement nameDescButton;
    @ExtendedFindBy(iosPredicate = "name == 'Price - Ascending'")
    private ExtendedWebElement priceAscButton;
    @ExtendedFindBy(iosPredicate = "name == 'Price - Descending'")
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
                throw new RuntimeException("Unknown sorting type " + sorting.toString());
        }
        return initPage(getDriver(), CatalogPageBase.class);
    }
}
