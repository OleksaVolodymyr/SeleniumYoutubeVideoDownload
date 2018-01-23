package com.rizon.youtube.bussinesobject;

import com.rizon.youtube.exceptions.NoResultFoundException;
import com.rizon.youtube.pages.ResultPage;

public class ResultPageBO {
    private ResultPage resultPage;
    private String url;

    public ResultPageBO() {
        resultPage = new ResultPage();
    }

    public void formatURLToDownload() throws NoResultFoundException {
        url = resultPage.getURL();
    }

    public boolean isURLValid() {
        return url.matches(".+:/+.+ss.+/.+");
    }

    public String getURL() {
        return url;
    }
}
