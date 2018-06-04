package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchResultPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public GoogleSearchResultPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,10,50);
        PageFactory.initElements(driver,this);
    }


    public boolean searchResultContains(String searchResultOutput) {
        return driver.findElements(By.partialLinkText(searchResultOutput)).size()>0;
    }
}
