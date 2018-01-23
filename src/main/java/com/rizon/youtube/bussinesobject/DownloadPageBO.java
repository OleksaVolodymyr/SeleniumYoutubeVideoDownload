package com.rizon.youtube.bussinesobject;

import com.rizon.youtube.pages.DownloadPage;
import com.rizon.youtube.pages.ResultPage;

public class DownloadPageBO {

    private DownloadPage downloadPage;
    private ResultPage resultPage;


    public DownloadPageBO() {
        downloadPage = new DownloadPage();
        resultPage = new ResultPage();
    }

    public void downloadVideo() {
        downloadPage.dowloadVideo(resultPage.getURL());
    }

    public boolean isVideoDownload() {
        return true;
    }


}
