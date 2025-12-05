package org.example.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserFactory {

    public static WebDriver getWebDriver(String browserName) {
        if (browserName == null) {
            browserName = "chrome"; // по умолчанию
        }

        switch (browserName.toLowerCase()) {
            case "yandex":
                return createYandexDriver();
            case "chrome":
            default:
                return createChromeDriver();
        }
    }

    // --- Chrome ---

    private static WebDriver createChromeDriver() {
        // Указываем путь к локальному chromedriver.exe
        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Tools\\chromedriver\\chromedriver.exe"
        );

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        // по желанию:
        // options.addArguments("--window-size=1920,1080");

        return new ChromeDriver(options);
    }

    // --- Яндекс-браузер (если понадобится) ---

    private static WebDriver createYandexDriver() {
        // Используем тот же chromedriver
        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Tools\\chromedriver\\chromedriver.exe"
        );

        ChromeOptions options = new ChromeOptions();

        // !!! ВАЖНО: сюда нужно подставить реальный путь к browser.exe Яндекс-браузера
        // Пример (у тебя может отличаться):
        // "C:\\Users\\ИмяПользователя\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe"
        options.setBinary("C:\\Path\\To\\Yandex\\browser.exe");

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        return new ChromeDriver(options);
    }
}
