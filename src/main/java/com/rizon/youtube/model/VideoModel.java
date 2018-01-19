package com.rizon.youtube.model;

import com.rizon.youtube.annotation.CSVElement;
import com.rizon.youtube.control.AbstractElement;
import org.openqa.selenium.WebElement;

public class VideoModel {

    @CSVElement
    private String name;


    public VideoModel() {

    }

    public VideoModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "VideoModel{" +
                "name='" + name + '\'' +
                '}';
    }
}
