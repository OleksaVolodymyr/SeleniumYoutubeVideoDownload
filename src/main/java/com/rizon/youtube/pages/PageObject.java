package com.rizon.youtube.pages;

import com.rizon.youtube.utils.ExtendedFieldDecorator;
import com.rizon.youtube.utils.WebDriverPool;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class PageObject {
    public PageObject(WebDriver driver) {
        PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
    }

    public PageObject() {
        this(WebDriverPool.getInstance());
    }
}
