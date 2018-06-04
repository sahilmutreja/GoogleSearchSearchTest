package testcases;

import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GoogleSearchPage;

public class TestAutoSuggestFeature extends BaseClass {

    @Test
    public void validateAutoSuggestFeature() {
        log("==========Starting Auto suggest test==========");
        GoogleSearchPage searchPage = new GoogleSearchPage(driver);
        String searchQuery = "SDET";
        searchPage.enterSearchQuery(searchQuery);
        boolean correctOptionVisible = searchPage.validateAutoCompleteContains("sdet full form");
        Assert.assertTrue(correctOptionVisible,"Correct option was not visible.");
        log("==========Auto suggest test completed==========");
    }
}
