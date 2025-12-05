package org.example;

import org.example.pages.ForgotPasswordPage;
import org.example.pages.LoginPage;
import org.example.pages.MainPage;
import org.example.pages.RegisterPage;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    // Данные тестового пользователя из формы регистрации
    private static final String USER_EMAIL = "rostislav255@gmail.com";
    private static final String USER_PASSWORD = "Rost12345!";

    /** 1. Логин через кнопку "Войти в аккаунт" на главной */
    @Test
    public void loginFromMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginToAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USER_EMAIL, USER_PASSWORD);

        assertTrue("После логина должна быть видна кнопка 'Оформить заказ'",
                mainPage.isMakeOrderButtonVisible());
    }

    /** 2. Логин через кнопку "Личный кабинет" */
    @Test
    public void loginFromPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USER_EMAIL, USER_PASSWORD);

        assertTrue("После логина должна быть видна кнопка 'Оформить заказ'",
                mainPage.isMakeOrderButtonVisible());
    }

    /** 3. Логин через форму регистрации (линк 'Войти') */
    @Test
    public void loginFromRegisterPage() {
        MainPage mainPage = new MainPage(driver);

        // Открываем форму регистрации через кнопку "Войти в аккаунт"
        mainPage.clickLoginToAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        // Возвращаемся на форму логина и логинимся
        loginPage = new LoginPage(driver);
        loginPage.login(USER_EMAIL, USER_PASSWORD);

        assertTrue("После логина должна быть видна кнопка 'Оформить заказ'",
                mainPage.isMakeOrderButtonVisible());
    }

    /** 4. Логин через страницу восстановления пароля (линк 'Войти') */
    @Test
    public void loginFromForgotPasswordPage() {
        MainPage mainPage = new MainPage(driver);

        // Открываем форму логина
        mainPage.clickLoginToAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRestorePasswordLink();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickLoginLink();

        // Снова на форме логина
        loginPage = new LoginPage(driver);
        loginPage.login(USER_EMAIL, USER_PASSWORD);

        assertTrue("После логина должна быть видна кнопка 'Оформить заказ'",
                mainPage.isMakeOrderButtonVisible());
    }
}
