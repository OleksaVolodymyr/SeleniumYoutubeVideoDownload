package com.rizon.youtube.bussinesobject;

import com.rizon.youtube.exceptions.NoResultFoundException;
import com.rizon.youtube.pages.ResultPage;

public class ResultPageBO {
    private ResultPage resultPage;

    public ResultPageBO() {
        resultPage = new ResultPage();
    }

    public boolean getVideoURLForDownload() throws NoResultFoundException {
        return resultPage.getURLForDownload().matches(".+:\\/+.+\\/.+");
    }
}
