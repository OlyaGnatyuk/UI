package org.example.steps;


import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import lombok.extern.slf4j.Slf4j;
import org.example.YandexWeather;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


@Slf4j
public class Weather {
    YandexWeather yandexWeather;
    WebDriver webDriver;
    private String cityName;

    @Дано("^Город (.*?)$")
    public void setCredentials(String cityName) {
        this.cityName = cityName;
        webDriver = new ChromeDriver();
        yandexWeather = new YandexWeather(webDriver);
    }

    @Когда("^Открываем страницу с прогнозом на 10 дней$")
    public void openPageWithForecast() {
        yandexWeather.clickOnInput();
        yandexWeather.enterCityName(this.cityName);
        yandexWeather.clickOnFirstCityInList();
        yandexWeather.clickTenDaysForecast();
    }


    @Тогда("^Выводим в консоль прогноз погоды на 10 дней$")
    public void logForecast() {
        yandexWeather.printTenDaysForecast();
    }

    @И("^Закрываем браузер$")
    public void closeBrowser() {
        if (webDriver != null) {
            webDriver.close();
        }
    }
}
