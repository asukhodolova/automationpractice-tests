package com.solvd.mydemoapp.mobile.pages.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.mydemoapp.mobile.dto.Sorting;
import org.openqa.selenium.WebDriver;

public abstract class SortingScreenBase extends AbstractPage implements ICommonScreen {

    public SortingScreenBase(WebDriver driver) {
        super(driver);
    }

    public abstract CatalogScreenBase selectSortingBy(Sorting sorting);
}
