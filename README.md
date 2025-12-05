# Диплом. Задание 3 — веб-приложение Stellar Burgers

Автоматизированные UI-тесты для веб-приложения [Stellar Burgers](https://stellarburgers.nomoreparties.site/).  
Проект покрывает ключевые пользовательские сценарии: работу конструктора бургера, авторизацию, переходы в личный кабинет и выход из аккаунта.

---

## Стек

- **Язык:** Java 11
- **Сборщик:** Maven
- **Тестовый фреймворк:** JUnit 4
- **UI-автоматизация:** Selenium WebDriver 4.7.2
- **Браузеры:** Google Chrome (по умолчанию), Yandex Browser
- **Паттерн:** Page Object

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
            BrowserFactory.java      # фабрика WebDriver (Chrome + Yandex)
          pages/
            MainPage.java            # главная страница с конструктором
            LoginPage.java           # страница логина
            RegisterPage.java        # страница регистрации
            ForgotPasswordPage.java  # страница восстановления пароля
            ProfilePage.java         # личный кабинет

    test/
      java/
        org/example/
          BaseTest.java              # общая настройка WebDriver
          ConstructorTabsTest.java   # тесты вкладок конструктора
          LoginTest.java             # тесты авторизации (4 способа)
          PersonalAccountTest.java   # переходы в личный кабинет и выход
          # (опционально) RegisterTest.java

