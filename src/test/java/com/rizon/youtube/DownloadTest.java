package com.rizon.youtube;

import com.rizon.youtube.bussinesobject.DownloadPageBO;
import com.rizon.youtube.bussinesobject.MainPageBO;
import com.rizon.youtube.bussinesobject.ResultPageBO;
import com.rizon.youtube.listener.CustomTestListener;
import com.rizon.youtube.utils.WebDriverPool;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomTestListener.class)
public class DownloadTest {

    @Test(dataProvider = "dataO", threadPoolSize = 2)
    public void test(String name) {
        MainPageBO youtubeMainPageBO = new MainPageBO();
        ResultPageBO resultPageBO = new ResultPageBO();
        DownloadPageBO downloadPageBO = new DownloadPageBO();
        youtubeMainPageBO.enterRequestAndSearch(name);
        Assert.assertTrue(youtubeMainPageBO.isSearchSuccess());
        resultPageBO.formatURLToDownload();
        Assert.assertTrue(resultPageBO.isURLValid());
        downloadPageBO.downloadVideo();
        Assert.assertTrue(downloadPageBO.isVideoDownload());

    }

//    @DataProvider(name = "data", parallel = true)
//    public Iterator<VideoModel> getDataFromCSV() {
//        return Parser.CSVParse("resources//video.csv", new VideoModel(), ",").iterator();
//    }

    @DataProvider(name = "dataO", parallel = true)
    public Object[][] getData() {
        return new Object[][]{
                new Object[]{"Post Malone - rockstar ft. 21 Savage"}/*,
                new Object[]{"Pendulum - Witchcraft"},
                new Object[]{"warlords of draenor"}
                new Object[]{"ajksdhahjsdadhsadlhjashjahdajlhsdjadhadsahdsjkahd"}*/};
    }

    @AfterTest
    public void close() {
        WebDriverPool.quit();
    }
}
