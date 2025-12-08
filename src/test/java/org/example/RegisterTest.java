package org.example;

import org.example.api.UserApiClient;
import org.example.pages.LoginPage;
import org.example.pages.RegisterPage;
import org.example.pages.MainPage;
import org.example.pages.ProfilePage;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RegisterTest extends BaseTest {

    // Данные пользователя, созданного в успешном тесте
    private String registeredEmail;
    private String registeredPassword;

    private final UserApiClient userApiClient = new UserApiClient();

    @After
    public void deleteRegisteredUser() {
        // После тестов удаляем пользователя через API, если он был создан
        if (registeredEmail != null && registeredPassword != null) {
            String token = userApiClient.loginAndGetToken(registeredEmail, registeredPassword);
            if (token != null) {
                userApiClient.deleteUser(token);
            }
        }
    }

    /**
     * Успешная регистрация пользователя.
     * Проверяем, что после регистрации открывается страница логина.
     */
    @Test
    public void userCanRegisterSuccessfully() {
        // BaseTest.setUp() уже создал driver и открыл главную страницу
        MainPage mainPage = new MainPage(driver);

        // Переходим в форму регистрации:
        // Личный кабинет -> ссылка «Зарегистрироваться»
        mainPage.clickPersonalAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();
        RegisterPage registerPage = new RegisterPage(driver);

        // Заполняем форму
        String name = "Autotest User";
        String email = "autotest_" + System.currentTimeMillis() + "@yandex.ru";
        String password = "strongPass123"; // >= 6 символов

        registerPage.register(name, email, password);

        // Сохраняем данные, чтобы удалить юзера через API в @After
        registeredEmail = email;
        registeredPassword = password;

        // После успешной регистрации должна открыться страница логина
        LoginPage loginAfterRegister = new LoginPage(driver);
        assertTrue(
                "После успешной регистрации должна открываться страница логина с кнопкой 'Войти'",
                loginAfterRegister.isLoginButtonVisible()
        );
    }

    /**
     * Нельзя зарегистрироваться с паролем короче 6 символов.
     */
    @Test
    public void userCannotRegisterWithShortPassword() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickPersonalAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();
        RegisterPage registerPage = new RegisterPage(driver);

        String name = "Short Pass User";
        String email = "shortpass_" + System.currentTimeMillis() + "@yandex.ru";
        String shortPassword = "12345"; // < 6 символов

        registerPage.register(name, email, shortPassword);

        // Проверяем, что появилась ошибка под полем пароля
        assertTrue(
                "Должна отображаться ошибка о некорректном пароле",
                registerPage.isPasswordErrorVisible()
        );
    }
}
