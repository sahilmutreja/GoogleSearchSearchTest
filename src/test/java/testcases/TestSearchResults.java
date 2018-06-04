package testcases;

import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GoogleSearchPage;
import pages.GoogleSearchResultPage;

public class TestSearchResults extends BaseClass {

    @Test
    public void validateSearchResults() {
        log("==========Starting Search results test==========");
        GoogleSearchPage searchPage = new GoogleSearchPage(driver);
        String searchQuery = "SDET full form";
        String searchResultOutput = "Software Development Engineer in Test";
        searchPage.enterSearchQuery(searchQuery);
        GoogleSearchResultPage searchResultPage = searchPage.getSearchResults();
        boolean resultsFound = searchResultPage.searchResultContains(searchResultOutput);
        Assert.assertTrue(resultsFound,"Correct result was found.");
        log("==========Search results test completed==========");
    }
}
