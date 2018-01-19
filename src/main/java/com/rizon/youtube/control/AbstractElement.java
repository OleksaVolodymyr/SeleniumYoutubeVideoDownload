package com.rizon.youtube.control;

import org.openqa.selenium.WebElement;

public class AbstractElement {
    protected WebElement webElement;

    public AbstractElement(WebElement webElement) {
        this.webElement = webElement;
    }
}
