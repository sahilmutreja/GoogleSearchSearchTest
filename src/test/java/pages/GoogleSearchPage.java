package pages;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchPage {

    private final WebDriverWait wait;
    WebDriver driver;

    @FindBy(name = "q")
    WebElement txtSearchBox;

    @FindBy(xpath = "//input[@type='button' and @value='Google Search']")
    WebElement btnSearch;

    public GoogleSearchPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,10,50);
        PageFactory.initElements(driver,this);
    }

    public void enterSearchQuery(String searchQuery) {
        txtSearchBox.sendKeys( searchQuery + Keys.SPACE);
    }

    public boolean validateAutoCompleteContains(String options) {
        String xpathExpression = String.format("//div[@role='option' and contains(.,'%s')]",options);
        return driver.findElement(By.xpath(xpathExpression)).isDisplayed();
    }

    public GoogleSearchResultPage getSearchResults() {
        btnSearch.click();
        return new GoogleSearchResultPage(driver);
    }
}
