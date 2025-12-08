# Диплом. Задание 3 — веб-приложение Stellar Burgers

Автоматизированные UI-тесты для веб-приложения [Stellar Burgers](https://stellarburgers.education-services.ru/).  
Покрываются основные пользовательские сценарии: конструктор бургера, авторизация, регистрация и личный кабинет.

---

## Технологический стек

- Java 11
- Maven
- JUnit 4
- Selenium WebDriver 4.7.2
- Браузеры: Google Chrome, Yandex Browser
- Паттерн: Page Object

---

## Структура проекта

```text
Diplom_3/
  pom.xml
  README.md

  src/
    main/
      java/
        org/example/
          browser/
            BrowserFactory.java      # фабрика WebDriver
          pages/
            MainPage.java            # главная страница
            LoginPage.java           # логин
            RegisterPage.java        # регистрация
            ForgotPasswordPage.java  # восстановление пароля
            ProfilePage.java         # личный кабинет

    test/
      java/
        org/example/
          BaseTest.java              # базовый класс
          ConstructorTabsTest.java   # тесты конструктора
          LoginTest.java             # тесты авторизации
          PersonalAccountTest.java   # тесты личного кабинета
          RegisterTest.java          # тесты регистрации
Покрытие тестами
Конструктор бургера

переключение вкладок «Булки», «Соусы», «Начинки»

подсветка активной вкладки

прокрутка к выбранному разделу

Авторизация (4 способа)

вход через кнопку «Войти в аккаунт» на главной странице

вход через кнопку «Личный кабинет»

вход со страницы регистрации («Войти»)

вход со страницы восстановления пароля

Регистрация

успешная регистрация нового пользователя

ошибка при вводе короткого пароля

Личный кабинет

переход в профиль из разных частей приложения

возврат в конструктор по кнопке и по логотипу

выход пользователя из аккаунта

Запуск тестов
Через Maven:

bash
Copy code
mvn clean test
