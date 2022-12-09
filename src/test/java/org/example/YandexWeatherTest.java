package org.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


@Slf4j
public class YandexWeatherTest {
    YandexWeather yandexWeather;
    WebDriver webDriver;

    @Before
    public void initialize() {
        webDriver = new ChromeDriver();
        yandexWeather = new YandexWeather(webDriver);
    }

    @Test
    public void testPageWithForecastOpened() {
        yandexWeather.clickOnInput();
        yandexWeather.enterCityName("Москва");
        yandexWeather.clickOnFirstCityInList();
        yandexWeather.clickTenDaysForecast();
        yandexWeather.printTenDaysForecast();
    }

    @After
    public void closePage() {
        if (webDriver != null) {
            webDriver.close();
        }
    }
}