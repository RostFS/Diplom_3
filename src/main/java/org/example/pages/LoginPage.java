package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;

    // Поле Email
    private final By emailInput =
            By.xpath("//label[text()='Email']/following-sibling::input");

    // Поле Пароль
    private final By passwordInput =
            By.xpath("//label[text()='Пароль']/following-sibling::input");

    // Кнопка "Войти"
    private final By loginButton =
            By.xpath("//button[text()='Войти']");

    // Линк "Зарегистрироваться" под формой логина
    private final By registerLink =
            By.xpath("//a[text()='Зарегистрироваться']");

    // Линк "Восстановить пароль"
    private final By restorePasswordLink =
            By.xpath("//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // ----- Действия -----

    public void setEmail(String email) {
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }

    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    public void clickRestorePasswordLink() {
        driver.findElement(restorePasswordLink).click();
    }
}
