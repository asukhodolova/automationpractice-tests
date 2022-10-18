package com.solvd.automationpractice.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.solvd.automationpractice.gui.pages.WomenPage;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class TopMenu extends AbstractUIObject {

    @FindBy(xpath = ".//a[@title='Women']")
    private ExtendedWebElement womenTab;
    @FindBy(xpath = ".//a[@title='Dresses']")
    private ExtendedWebElement dressesTab;
    @FindBy(xpath = ".//a[@title='T-shirts']")
    private ExtendedWebElement tShirtsTab;

    public TopMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public WomenPage openWomenTab() {
        womenTab.click();
        return new WomenPage(driver);
    }
}
