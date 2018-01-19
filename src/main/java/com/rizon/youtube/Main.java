package com.rizon.youtube;

import com.rizon.youtube.model.VideoModel;
import com.rizon.youtube.utils.Parser;
import org.openqa.selenium.WebDriver;

public class Main {
    static WebDriver driver;

    public static void main(String[] args) {
//        //Post Malone - rockstar ft. 21 Savage
//         //fafsadfaskfalfksasdada
//        String name = "Post Malone - rockstar ft. 21 Savage";
//        MainPage youtubeMainPage = new MainPage();
//        youtubeMainPage.enterNameAndSearch(name);
//        ResultPage resultPage = new ResultPage();
//        String url;
//        url = resultPage.getURLForDownload(name);
//        if (url != null) {
//            System.out.println(url);
//            WebDriver driver = WebDriverPool.getInstance();
//            driver.navigate().to(url);
//            new WebDriverWait(driver, 30)
//                    .until(new ExpectedCondition<Boolean>() {
//
//                        @Nullable
//                        @Override
//                        public Boolean apply(@Nullable WebDriver driver) {
//                            return driver.findElement(By.xpath("//div[@class='def-btn-box']")).isDisplayed() &&
//                                    driver.findElement(By.xpath("//div[@class='def-btn-box']")).isEnabled();
//                        }
//                    });
//        } else System.out.println("No result found");
        System.out.println(Parser.CSVParse("resources//video.csv", new VideoModel(), ","));
    }

    public static int editDist(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[] d1;
        int[] d2 = new int[n + 1];

        for (int i = 0; i <= n; i++)
            d2[i] = i;

        for (int i = 1; i <= m; i++) {
            d1 = d2;
            d2 = new int[n + 1];
            for (int j = 0; j <= n; j++) {
                if (j == 0) d2[j] = i;
                else {
                    int cost = (s1.charAt(i - 1) != s2.charAt(j - 1)) ? 1 : 0;
                    if (d2[j - 1] < d1[j] && d2[j - 1] < d1[j - 1] + cost)
                        d2[j] = d2[j - 1] + 1;
                    else if (d1[j] < d1[j - 1] + cost)
                        d2[j] = d1[j] + 1;
                    else
                        d2[j] = d1[j - 1] + cost;
                }
            }
        }
        return d2[n];
    }
}
