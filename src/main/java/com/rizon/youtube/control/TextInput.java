package com.rizon.youtube.control;

import org.openqa.selenium.WebElement;

public class TextInput extends AbstractElement {
    public TextInput(WebElement webElement) {
        super(webElement);
    }

    public void sendKeys(String text) {
        webElement.sendKeys(text);
    }

    public void submit() {
        webElement.submit();
    }

    public String getText() {
        return webElement.getText();
    }
}
