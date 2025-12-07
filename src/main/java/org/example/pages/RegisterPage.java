package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private final WebDriver driver;

    // Поле "Имя"
    private final By nameInput =
            By.xpath("//label[text()='Имя']/following-sibling::input");

    // Поле "Email"
    private final By emailInput =
            By.xpath("//label[text()='Email']/following-sibling::input");

    // Поле "Пароль"
    private final By passwordInput =
            By.xpath("//label[text()='Пароль']/following-sibling::input");

    // Кнопка "Зарегистрироваться"
    private final By registerButton =
            By.xpath("//button[text()='Зарегистрироваться']");

    // Ссылка "Войти" на странице регистрации
    private final By loginLink =
            By.xpath("//a[text()='Войти']");

    // Сообщение об ошибке "Некорректный пароль"
    private final By passwordError =
            By.xpath("//p[contains(text(),'Некорректный пароль')]");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    // ----- Действия для регистрации -----

    public void setName(String name) {
        driver.findElement(nameInput).clear();
        driver.findElement(nameInput).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    /** Полная регистрация: заполняем все поля и жмём "Зарегистрироваться" */
    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    // ----- То, что уже использует твой LoginTest -----

    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    // ----- Ошибка пароля -----

    public String getPasswordErrorText() {
        return driver.findElement(passwordError).getText();
    }
}
