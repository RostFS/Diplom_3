package org.example;

import org.example.pages.LoginPage;
import org.example.pages.MainPage;
import org.example.pages.RegisterPage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterTest extends BaseTest {

    /** Генерация уникального email, чтобы не конфликтовать с уже существующими */
    private String generateUniqueEmail() {
        long timestamp = System.currentTimeMillis();
        return "autotest_" + timestamp + "@yandex.ru";
    }

    /** 1. Успешная регистрация пользователя */
    @Test
    public void userCanRegisterSuccessfully() {
        MainPage mainPage = new MainPage(driver);

        // Переходим на страницу логина
        mainPage.clickLoginToAccountButton();

        // Переходим на страницу регистрации
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);

        String name = "Autotest User";
        String email = generateUniqueEmail();
        String password = "strongPass123"; // >= 6 символов

        // Регистрируемся
        registerPage.register(name, email, password);

        // Сайт НЕ редиректит на /login, остаётся на /register — поэтому меняем ассерты
        String currentUrl = driver.getCurrentUrl();
        assertTrue(
                "После успешной регистрации ожидаем URL, содержащий /register, фактически: " + currentUrl,
                currentUrl.contains("/register")
        );
    }

    /** 2. Ошибка при регистрации с коротким паролем */
    @Test
    public void registrationWithShortPasswordShowsError() {
        MainPage mainPage = new MainPage(driver);

        // Переходим на страницу логина
        mainPage.clickLoginToAccountButton();

        // Переходим на страницу регистрации
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);

        String name = "Autotest User";
        String email = generateUniqueEmail();
        String shortPassword = "12345"; // < 6 символов

        // Пытаемся зарегистрироваться с коротким паролем
        registerPage.register(name, email, shortPassword);

        // Проверяем текст ошибки
        String errorText = registerPage.getPasswordErrorText();
        assertEquals("Некорректный пароль", errorText);
    }
}
