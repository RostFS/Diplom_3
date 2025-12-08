package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // --- Поля формы регистрации ---
    private final By nameInput =
            By.xpath("//label[text()='Имя']/following-sibling::input");

    private final By emailInput =
            By.xpath("//label[text()='Email']/following-sibling::input");

    private final By passwordInput =
            By.xpath("//label[text()='Пароль']/following-sibling::input");

    // Кнопка "Зарегистрироваться"
    private final By registerButton =
            By.xpath("//button[text()='Зарегистрироваться']");

    // Сообщение об ошибке под полем пароля
    // (текст у Практикума — "Некорректный пароль")
    private final By passwordError =
            By.xpath("//p[contains(@class,'input__error') and contains(text(),'Некорректный пароль')]");

    // Линк "Войти" на странице регистрации
    private final By loginLink =
            By.xpath("//a[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // ---------- Действия с полями ----------

    public void setName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(nameInput)).clear();
        driver.findElement(nameInput).sendKeys(name);
    }

    public void setEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailInput)).clear();
        driver.findElement(emailInput).sendKeys(email);
    }

    public void setPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput)).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    /**
     * Заполняет форму и нажимает "Зарегистрироваться".
     */
    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    /**
     * Переход по ссылке "Войти" (используется в тестах логина).
     */
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    /**
     * Проверка, что появилось сообщение об ошибке некорректного пароля.
     */
    public boolean isPasswordErrorVisible() {
        return !driver.findElements(passwordError).isEmpty()
                && wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).isDisplayed();
    }
}
