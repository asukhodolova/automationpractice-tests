package com.solvd.mydemoapp.mobile.pages.common;

import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class AbstractScreenBase extends AbstractPage implements IMobileUtils, ICommonScreen {

    public AbstractScreenBase(WebDriver driver) {
        super(driver);
    }
}
