package com.rizon.youtube.pages;

import com.rizon.youtube.control.Button;
import com.rizon.youtube.control.Label;
import com.rizon.youtube.control.TextInput;
import com.rizon.youtube.utils.WebDriverPool;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class MainPage extends PageObject {
    private static Logger LOG = Logger.getLogger(MainPage.class);
    private static final String XPATH_SEARCH_FORM = "//input[@id='search']";
    private static final String XPATH_SEARCH_BUTTON = "//button[@id='search-icon-legacy']";
    private static final String ID_RESULT_TEXT = "result-count";


    @FindBy(xpath = XPATH_SEARCH_FORM)
    private TextInput searchForm;

    @FindBy(xpath = XPATH_SEARCH_BUTTON)
    private Button searchButton;

    @FindBy(id = ID_RESULT_TEXT)
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

    public String countOfRecords() {
        new WebDriverWait(WebDriverPool.getInstance(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("result-count")));
        //LOG.info(countOfResult.getText());
        return countOfResult.getText();
    }
}
