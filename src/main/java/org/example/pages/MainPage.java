package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // --- Локаторы конструктора ---
    private final By bunsTab = By.xpath("//span[text()='Булки']/parent::div");
    private final By saucesTab = By.xpath("//span[text()='Соусы']/parent::div");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']/parent::div");
    private final By activeTab = By.xpath("//div[contains(@class,'tab_tab_type_current')]");

    // --- Локаторы для логина ---
    // Кнопка "Войти в аккаунт" на главной
    private final By loginToAccountButton = By.xpath("//button[text()='Войти в аккаунт']");

    // Кнопка/ссылка "Личный Кабинет" в шапке
    private final By personalAccountButton =
            By.xpath("//p[text()='Личный Кабинет']/parent::a");

    // Кнопка "Оформить заказ" — удобный маркер, что пользователь залогинен
    private final By makeOrderButton =
            By.xpath("//button[contains(text(),'Оформить заказ')]");

    // Модальный оверлей, который иногда перекрывает хедер
    private final By modalOverlay =
            By.xpath("//div[contains(@class,'Modal_modal_overlay')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // ---------- Методы для конструктора ----------

    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }

    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
    }

    public String getActiveTabText() {
        WebElement active = driver.findElement(activeTab);
        return active.getText();
    }

    // ---------- Методы для логина / личного кабинета ----------

    /** Клик по кнопке "Войти в аккаунт" на главной */
    public void clickLoginToAccountButton() {
        driver.findElement(loginToAccountButton).click();
    }

    /** Клик по кнопке "Личный Кабинет" в шапке с учётом модального оверлея */
    public void clickPersonalAccountButton() {
        // Если поверх страницы висит оверлей модального окна — ждём, пока он исчезнет
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(modalOverlay));
        } catch (Exception ignored) {
            // Оверлея могло и не быть — это нормально
        }

        // Ждём, пока кнопка станет кликабельной, и кликаем по ней
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton))
                .click();
    }

    /** Проверяем, что кнопка "Оформить заказ" видна (пользователь авторизован) */
    public boolean isMakeOrderButtonVisible() {
        return !driver.findElements(makeOrderButton).isEmpty()
                && driver.findElement(makeOrderButton).isDisplayed();
    }
}
