package com.rizon.youtube.control;

import org.openqa.selenium.WebElement;

public class Label extends AbstractElement {

    public Label(WebElement webElement) {
        super(webElement);
    }

    public String getText() {
        return webElement.getText();
    }

    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

    public String getAttribute(String s) {
        return webElement.getAttribute(s);
    }

    public boolean isEnable(){
        return webElement.isEnabled();
    }
}