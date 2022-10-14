package com.solvd.automationpractice.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.solvd.automationpractice.gui.pages.HomePage;
import com.solvd.automationpractice.gui.pages.SearchResultPage;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Header extends AbstractUIObject {

    @FindBy(linkText = "My Store")
    private ExtendedWebElement homeLink;
    @FindBy(name = "search_query")
    private ExtendedWebElement searchInput;
    @FindBy(name = "submit_search")
    private ExtendedWebElement searchButton;

    public Header(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public HomePage openHomePage() {
        homeLink.click();
        return new HomePage(driver);
    }

    public SearchResultPage performSearch(String searchValue) {
        searchInput.type(searchValue);
        searchButton.click();
        return new SearchResultPage(driver);
    }
}
