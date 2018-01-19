package com.rizon.youtube.bussinesobject;

import com.rizon.youtube.pages.MainPage;

public class MainPageBO {
    private MainPage youtubeMainPage;

    public MainPageBO() {
        youtubeMainPage = new MainPage();
    }

    public boolean isSearchSuccess(String searchRequest) {
        youtubeMainPage.enterNameAndSearch(searchRequest);
        return youtubeMainPage.isSearchSuccess(searchRequest);
    }
}
