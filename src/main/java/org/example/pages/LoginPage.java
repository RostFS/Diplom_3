package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // --- Локаторы ---
    // поле логина (email)
    private final By emailInput = By.xpath("//input[@name='name']");
    // кнопка «Войти»
    private final By loginButton = By.xpath("//button[text()='Войти']");
    // ссылка «Зарегистрироваться»
    private final By registerLink = By.xpath("//a[@href='/register']");
    // ссылка «Восстановить пароль»
    private final By restorePasswordLink = By.xpath("//a[@href='/forgot-password']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        // Увеличили таймаут до 10 секунд
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Ждём, пока загрузится страница входа (если заголовка нет — просто игнорируем)
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//h2[contains(text(),'Вход')]")
            ));
        } catch (TimeoutException ignored) {
            // Страница могла открыться в другом состоянии — не критично
        }
    }

    // --- Ввод email ---
    public void setEmail(String email) {
        WebElement emailField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailInput)
        );
        emailField.clear();
        emailField.sendKeys(email);
    }

    // --- Ввод пароля (с перебором возможных локаторов) ---
    public void setPassword(String password) {
        // Набор возможных локаторов поля пароля
        List<By> possibleLocators = Arrays.asList(
                By.xpath("//input[@name='password']"),
                By.xpath("//input[@name='Password']"),
                By.xpath("//input[@name='Пароль']"),
                By.xpath("//input[@type='password']"),
                By.cssSelector("input[type='password']")
        );

        for (By locator : possibleLocators) {
            try {
                WebElement passwordField = new WebDriverWait(driver, Duration.ofSeconds(3))
                        .until(ExpectedConditions.visibilityOfElementLocated(locator));
                passwordField.clear();
                passwordField.sendKeys(password);
                return; // получилось — выходим из метода
            } catch (TimeoutException ignored) {
                // Пробуем следующий локатор
            }
        }
        throw new NoSuchElementException("Не удалось найти поле пароля ни по одному из локаторов");
    }

    // --- Клик по кнопке «Войти» ---
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    // --- Комплексный логин ---
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }

    // --- Переход по ссылке «Зарегистрироваться» ---
    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    // --- Переход по ссылке «Восстановить пароль» ---
    public void clickRestorePasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(restorePasswordLink)).click();
    }

    /**
     * Проверяем, что страница логина действительно открыта:
     * признак — видна кнопка «Войти».
     */
    public boolean isLoginButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
