package org.example;

import org.example.pages.LoginPage;
import org.example.pages.MainPage;
import org.example.pages.ProfilePage;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class PersonalAccountTest extends BaseTest {

    // Данные тестового пользователя (как в LoginTest)
    private static final String USER_EMAIL = "rostislav255@gmail.com";
    private static final String USER_PASSWORD = "Rost12345!";

    // Вспомогательный метод логина
    private void login() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginToAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USER_EMAIL, USER_PASSWORD);
    }

    /** 1. Переход по клику на «Личный кабинет» из главной страницы */
    @Test
    public void openPersonalAccountFromMainPage() {
        login(); // сначала логинимся

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        ProfilePage profilePage = new ProfilePage(driver);
        assertTrue("Страница профиля должна быть открыта",
                profilePage.isProfilePageOpened());
    }

    /** 2. Переход из личного кабинета в конструктор по кнопке «Конструктор» */
    @Test
    public void goToConstructorFromPersonalAccountByConstructorButton() {
        login();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        ProfilePage profilePage = new ProfilePage(driver);
        assertTrue("Страница профиля должна быть открыта",
                profilePage.isProfilePageOpened());

        profilePage.clickConstructorButton();

        assertTrue("После клика по кнопке «Конструктор» должна открыться главная страница",
                mainPage.isMakeOrderButtonVisible());
    }

    /** 3. Переход из личного кабинета в конструктор по клику на логотип Stella */
    @Test
    public void goToConstructorFromPersonalAccountByLogo() {
        login();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        ProfilePage profilePage = new ProfilePage(driver);
        assertTrue("Страница профиля должна быть открыта",
                profilePage.isProfilePageOpened());

        profilePage.clickLogoButton();

        assertTrue("После клика по логотипу должна открыться главная страница",
                mainPage.isMakeOrderButtonVisible());
    }

    /** 4. Выход из личного кабинета по кнопке «Выход» */
    @Test
    public void logoutFromPersonalAccount() {
        login();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        ProfilePage profilePage = new ProfilePage(driver);
        assertTrue("Страница профиля должна быть открыта",
                profilePage.isProfilePageOpened());

        // Нажимаем «Выход»
        profilePage.clickLogoutButton();

        // Явно ждём редирект на страницу логина
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/login"));

        // Проверяем URL
        String currentUrl = driver.getCurrentUrl();
        assertTrue(
                "После выхода должен быть redirect на login-страницу. Текущий URL: " + currentUrl,
                currentUrl.contains("/login")
        );
    }
}
