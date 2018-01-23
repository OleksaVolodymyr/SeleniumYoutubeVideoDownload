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
    private static final String XPATH_CONTENT = "//div[@class='promo-title style-scope ytd-background-promo-renderer']";
    //    private static Logger LOG = Logger.getLogger(ResultPage.class);
    @FindBy(xpath = XPATH_VIDEO)
    private List<Label> video;

    public String getURL() throws NoResultFoundException {
        new WebDriverWait(WebDriverPool.getInstance(), 10)
                .until((ExpectedCondition<Boolean>) driver ->  driver.findElement(By.xpath(XPATH_VIDEO)).getAttribute("title")
                        .contains("Post Malone - rockstar ft. 21 Savage"));
        Boolean checkElementPresent = WebDriverPool.getInstance()
                .findElements(By.xpath(XPATH_CONTENT))
                .size() > 0;
        if (checkElementPresent) {
            throw new NoResultFoundException("No result found");
        }
        String url = video.get(0).getAttribute("href");
        return url.substring(0, url.indexOf(".") + 1) + "ss" + url.substring(url.indexOf(".") + 1, url.length());

    }

}

