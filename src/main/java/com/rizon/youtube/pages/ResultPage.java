package com.rizon.youtube.pages;

import com.rizon.youtube.control.Label;
import com.rizon.youtube.exceptions.NoResultFoundException;
import com.rizon.youtube.utils.WebDriverPool;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.util.List;

public class ResultPage extends PageObject {

    private static final String XPATH_VIDEO = "//h3//a[@id='video-title']";
    private static Logger LOG = Logger.getLogger(ResultPage.class);
    @FindBy(xpath = XPATH_VIDEO)
    private List<Label> video;

    public String getURLForDownload() throws NoResultFoundException {
        new WebDriverWait(WebDriverPool.getInstance(), 10).until(new ExpectedCondition<Boolean>() {
            @Nullable
            @Override
            public Boolean apply(@Nullable WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return window.document.readyState")
                        .equals("complete");
            }
        });
        Boolean checkElementPresent = WebDriverPool.getInstance()
                .findElements(By.xpath("//div[@class='promo-title style-scope ytd-background-promo-renderer']"))
                .size() > 0;
        if (checkElementPresent) {
            //return null;
            throw new NoResultFoundException("No result");
        }
        String url = video.get(0).getAttribute("href");
        //LOG.info(url.substring(0, url.indexOf(".") + 1) + "ss" + url.substring(url.indexOf(".") + 1, url.length()));
        return url.substring(0, url.indexOf(".") + 1) + "ss" + url.substring(url.indexOf(".") + 1, url.length());

    }

}

