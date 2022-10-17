package com.solvd.automationpractice.tests.smoke;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.core.foundation.utils.tag.Priority;
import com.qaprosoft.carina.core.foundation.utils.tag.TestPriority;
import com.solvd.automationpractice.gui.pages.HomePage;
import com.solvd.automationpractice.gui.pages.SearchResultPage;
import com.solvd.automationpractice.utils.DataUtils;
import com.zebrunner.agent.core.annotation.TestLabel;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SearchTest implements IAbstractTest {

    private static final List<String> SEARCHABLE_TEXTS = Arrays.asList("blouse", "dREss");

    @Test(description = "Perform search and verify search results are correct", dataProvider = "searchValues")
    @MethodOwner(owner = "asukhodolova")
    @TestPriority(Priority.P0)
    @TestLabel(name = "feature", value = {"web", "smoke"})
    public void testSearchSuccessful(String searchValue) {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");

        SearchResultPage searchResultPage = homePage.getHeader().performSearch(searchValue);
        Assert.assertTrue(searchResultPage.isPageOpened(), "Search results page is not opened");

        Assert.assertTrue(searchResultPage.getFoundProducts().size() != 0, "No search results");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(searchResultPage.getSearchableText(), searchValue.toUpperCase());
        softAssert.assertEquals(searchResultPage.getNumberOfResultsFound(), searchResultPage.getFoundProducts().size());

        for (String searchResultName :
                searchResultPage.getFoundProducts().stream().map(p -> p.getProductName()).collect(Collectors.toList())) {
            softAssert.assertTrue(StringUtils.containsIgnoreCase(searchResultName, searchValue),
                    String.format("Found product %s does not have %s", searchResultName, searchValue));
        }
        softAssert.assertAll();
    }

    @DataProvider(name = "searchValues")
    public static Object[][] searchValues() {
        return DataUtils.listToDataProviderArray(SEARCHABLE_TEXTS);
    }
}
