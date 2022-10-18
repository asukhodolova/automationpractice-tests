package com.solvd.mydemoapp.mobile.pages.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.mydemoapp.mobile.dto.Sorting;
import org.openqa.selenium.WebDriver;

public abstract class SortingPageBase extends AbstractPage {

    public SortingPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract CatalogPageBase selectSortingBy(Sorting sorting);
}
