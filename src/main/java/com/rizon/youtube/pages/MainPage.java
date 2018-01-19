package com.rizon.youtube.pages;

import com.rizon.youtube.control.Button;
import com.rizon.youtube.control.Label;
import com.rizon.youtube.control.TextInput;
import com.rizon.youtube.utils.WebDriverPool;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class MainPage extends PageObject {

    private static final String XPATH_SEARCH_FORM = "//input[@id='search']";
    private static final String XPATH_SEARCH_BUTTON = "//button[@id='search-icon-legacy']";


    @FindBy(xpath = XPATH_SEARCH_FORM)
    private TextInput searchForm;

    @FindBy(xpath = XPATH_SEARCH_BUTTON)
    private Button searchButton;

    @FindBy(xpath = "//div[@id='contents']")
    private List<Label> content;

    @FindBy(xpath = "//div[@id='container']")
    private Label countOfResult;

    public void enterNameAndSearch(String searchRequest) {
        new WebDriverWait(WebDriverPool.getInstance(), 30).until(new ExpectedCondition<Boolean>() {
            @Nullable
            @Override
            public Boolean apply(@Nullable WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return window.document.readyState")
                        .equals("complete");
            }
        });
        searchForm.sendKeys(searchRequest);
        searchButton.click();
    }

    public boolean isSearchSuccess(String searchRequest) {
        // return content.size() != 0;
        new WebDriverWait(WebDriverPool.getInstance(), 30).until(new ExpectedCondition<Boolean>() {
            @Nullable
            @Override
            public Boolean apply(@Nullable WebDriver driver) {
                return driver.findElement(By.xpath("//div[@id='container']")).isDisplayed();
            }
        });
        return countOfResult.getText().matches("About \\d+");
    }
}
