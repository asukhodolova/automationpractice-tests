package com.solvd.automationpractice.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.automationpractice.gui.components.Header;
import com.solvd.automationpractice.gui.components.ProductItem;
import com.solvd.automationpractice.gui.components.ProductList;
import com.solvd.automationpractice.utils.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultPage extends AbstractPage {

    @FindBy(id = "header")
    private Header header;
    @FindBy(id = "search")
    private ExtendedWebElement searchBlock;
    @FindBy(className = "lighter")
    private ExtendedWebElement searchableTextLabel;
    @FindBy(className = "heading-counter")
    private ExtendedWebElement numberOfResultsLabel;
    @FindBy(xpath = "//*[contains(@class,'product_list')]")
    private ProductList productListBlock;

    public SearchResultPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(searchBlock);
    }

    public Header getHeader() {
        return header;
    }

    public String getSearchableText() {
        return searchableTextLabel.getText().replaceAll("\"", "");
    }

    public int getNumberOfResultsFound() {
        return Integer.parseInt(StringUtils.getNumbersFromString(numberOfResultsLabel.getText()));
    }

    public List<ProductItem> getFoundProducts() {
        return productListBlock.getProductItems();
    }
}
