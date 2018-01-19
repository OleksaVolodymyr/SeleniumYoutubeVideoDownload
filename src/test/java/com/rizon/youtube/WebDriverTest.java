package com.rizon.youtube;

import com.rizon.youtube.bussinesobject.MainPageBO;
import com.rizon.youtube.listener.CustomTestListener;
import com.rizon.youtube.utils.WebDriverPool;
import org.testng.annotations.*;

@Listeners(CustomTestListener.class)
public class WebDriverTest {

    @Test(threadPoolSize = 2, dataProvider = "dataO")
    public void test(String name) {
        MainPageBO youtubeMainPageBO = new MainPageBO();
    }

    @DataProvider(name = "dataO", parallel = true)
    public Object[][] getData() {
        return new Object[][]{
                new Object[]{"Post Malone - rockstar ft. 21 Savage"},
                new Object[]{"Pendulum - Witchcraft"}/*,
                new Object[]{"ajksdhahjsdadhsadlhjashjahdajlhsdjadhadsahdsjkahd"}*/};
    }

    @AfterMethod
    public void close() {
        WebDriverPool.quit();
    }
}
