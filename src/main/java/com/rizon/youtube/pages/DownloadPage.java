package com.rizon.youtube.pages;

import com.rizon.youtube.control.Button;
import com.rizon.youtube.utils.WebDriverPool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;

public class DownloadPage extends PageObject {

    private static final String XPATH_DOWNLOAD_BUTTON = "//div[@class='def-btn-box']";
    private WebDriver driver = WebDriverPool.getInstance();

    @FindBy(xpath = XPATH_DOWNLOAD_BUTTON)
    private Button donwloadButton;

    public boolean dowloadVideo(String url) {
        driver.navigate().to(url);
        new WebDriverWait(driver, 30)
                .until(new ExpectedCondition<Boolean>() {

                    @Nullable
                    @Override
                    public Boolean apply(@Nullable WebDriver driver) {
                        return driver.findElement(By.xpath(XPATH_DOWNLOAD_BUTTON)).isDisplayed() &&
                                driver.findElement(By.xpath(XPATH_DOWNLOAD_BUTTON)).isEnabled();
                    }
                });
        donwloadButton.click();
        return true;
    }
}
