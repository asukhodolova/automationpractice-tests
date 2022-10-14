package com.solvd.automationpractice.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.automationpractice.gui.components.Header;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(id = "header")
    private Header header;
    @FindBy(id = "homepage-slider")
    private ExtendedWebElement homeSlider;

    public HomePage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(homeSlider);
    }

    public Header getHeader() {
        return header;
    }
}
