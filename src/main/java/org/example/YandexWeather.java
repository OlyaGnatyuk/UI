package org.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@Slf4j
public class YandexWeather {
    private static final int TIME_OUT_IN_SECONDS = 30;
    private static final int SLEEP_IN_MILLIS = 500;
    private static final String URI = "https://yandex.ru/pogoda/";
    private final WebDriver webDriver;
    private final WebDriverWait webDriverWait;

    public YandexWeather(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriver.manage().window().maximize();
        webDriver.get(URI);
        webDriverWait = new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS, SLEEP_IN_MILLIS);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css = "[class=\"mini-suggest-form__input mini-suggest__input\"]")
    public WebElement cityInput;

    @FindBy(css = "[class=\"mini-suggest__popup-inner\"] a")
    public List<WebElement> suggestedCityList;

    @FindBy(css = "[class=\"forecast-briefly__header-buttons\"] a")
    public List<WebElement> forecastLinks;

    @FindBy(css = "[class=\"forecast-details__title\"]")
    public List<WebElement> daysHeaders;

    @FindBy(css = "[class=\"weather-table__body-cell weather-table__body-cell_type_daypart weather-table__body-cell_wrapper\"]")
    public List<WebElement> daysTemperature;

    public void clickOnInput() {
        cityInput.click();
    }

    public void enterCityName(String cityName) {
        Actions actions = new Actions(webDriver);
        actions.sendKeys(cityName)
                .perform();
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(suggestedCityList));
    }

    public void clickOnFirstCityInList() {
        suggestedCityList.get(0).click();
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(forecastLinks));
    }

    public void clickTenDaysForecast() {
        forecastLinks.get(0).click();
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(daysHeaders));
    }

    public void printTenDaysForecast() {
        int temperatureCount = 0;
        for (WebElement e: daysHeaders) {
            log.info(e.getText());
            for (int i = 0; i < 4; i++) {
                log.info(daysTemperature.get(temperatureCount).getText());
                temperatureCount++;
            }
        }
    }

}


