package com.rizon.youtube.bussinesobject;

import com.rizon.youtube.pages.MainPage;

public class MainPageBO {
    private MainPage youtubeMainPage;

    public MainPageBO() {
        youtubeMainPage = new MainPage();
    }

    public void enterRequestAndSearch(String searchRequest) {
        youtubeMainPage.enterNameAndSearch(searchRequest);
    }

    public boolean isSearchSuccess() {
        return youtubeMainPage.countOfRecords().matches("About (\\d+,\\d+|\\d+,\\d+,\\d+) .+");
    }
}
