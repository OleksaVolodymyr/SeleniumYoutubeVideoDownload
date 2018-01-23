package com.rizon.youtube;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

public class Main {
    static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        driver.navigate().to("https://www.youtube.com");
        WebElement searchForm = driver.findElement(By.xpath("//input[@id='search']"));
        searchForm.sendKeys("warlords of draenor");
        driver.findElement(By.xpath("//button[@id='search-icon-legacy']")).click();
        Wait<WebDriver> fw = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchMethodException.class);
        WebElement element = fw.until(driver ->
                driver.findElement(By.xpath("//h3//a[@id='video-title']")).getAttribute("title")
                        .contains("Warlords") ? driver.findElement(By.xpath("//h3//a[@id='video-title']")) : null);
        System.out.println(element.getText());
//        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
//            @Nullable
//            @Override
//            public Boolean apply(@Nullable WebDriver driver) {
//                System.out.println(driver.findElement(By.xpath("//h3//a[@id='video-title']")).getAttribute("title"));
//                return driver.findElement(By.xpath("//h3//a[@id='video-title']")).getAttribute("title")
//                        .contains("Warlords");
//            }
//        });

//        new WebDriverWait(driver, 10)
//                .until(ExpectedConditions.visibilityOfElementLocated(By.id("result-count")));
//        WebElement count = driver.findElement(By.id("result-count"));
//        System.out.println(count.getText());
//        System.out.println(count.getText().matches("About \\d+,\\d+ .+"));

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
