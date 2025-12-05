package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // --- Локаторы хедера ---
    // Кнопка "Личный Кабинет" в шапке
    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
    // Кнопка "Конструктор" в шапке
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    // Логотип Stellar Burgers в шапке
    private final By logoButton = By.xpath(".//div[contains(@class,'AppHeader_header__logo')]");

    // --- Локаторы внутри личного кабинета ---
    // Активный пункт меню "Профиль" слева
    private final By profileTabActive = By.xpath(
            ".//a[contains(@class,'Account_link_active') and text()='Профиль']"
    );
    // Кнопка "Выход" слева
    private final By logoutButton = By.xpath(".//button[text()='Выход']");

    // Заголовок конструктора "Соберите бургер" на главной
    private final By constructorHeader = By.xpath(".//h1[text()='Соберите бургер']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // --- Действия в хедере ---

    // Переход в Личный кабинет по клику на кнопку в шапке
    public void clickPersonalAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
    }

    // Переход в конструктор по кнопке "Конструктор" в шапке
    public void clickConstructorButton() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
    }

    // Переход в конструктор по клику на логотип
    public void clickLogoButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoButton)).click();
    }

    // --- Элементы личного кабинета ---

    // Проверка, что открыта страница профиля (активен пункт меню "Профиль")
    public boolean isProfilePageOpened() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(profileTabActive)).isDisplayed();
    }

    // Клик по кнопке "Выход"
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    // --- Проверки навигации ---

    // Проверка, что мы в конструкторе (главная страница)
    public boolean isConstructorOpened() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(constructorHeader)).isDisplayed();
    }
}
