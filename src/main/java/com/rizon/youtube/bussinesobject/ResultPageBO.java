package com.rizon.youtube.bussinesobject;

import com.rizon.youtube.pages.ResultPage;

public class ResultPageBO {
    private ResultPage resultPage;

    public ResultPageBO() {
        resultPage = new ResultPage();
    }

    public String getVideoURLForDownload() {
        return resultPage.getURLForDownload();
    }
}
