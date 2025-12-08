Диплом. Задание 3 — веб-приложение Stellar Burgers

Автоматизированные UI-тесты для веб-приложения Stellar Burgers:
конструктор бургера, авторизация, регистрация и личный кабинет.

Тестируемый стенд: https://stellarburgers.education-services.ru/

Технологический стек

Java 11

Maven

JUnit 4

Selenium WebDriver 4.7.2

Браузеры: Google Chrome, Yandex Browser

Паттерн: Page Object

Структура проекта
Diplom_3/
pom.xml
README.md

src/
main/
java/
org/example/
browser/
BrowserFactory.java          # фабрика WebDriver
pages/
MainPage.java                # главная страница
LoginPage.java               # логин
RegisterPage.java            # регистрация
ForgotPasswordPage.java      # восстановление пароля
ProfilePage.java             # личный кабинет

    test/
      java/
        org/example/
          BaseTest.java                  # базовый тест
          ConstructorTabsTest.java       # тесты конструктора
          LoginTest.java                 # тесты авторизации
          PersonalAccountTest.java       # тесты личного кабинета
          RegisterTest.java              # тесты регистрации
          api/
            UserApiClient.java           # API-клиент для удаления тестовых пользователей

Покрытие тестами
🔧 Конструктор бургера

Переключение вкладок: «Булки», «Соусы», «Начинки»

Подсветка активной вкладки

Прокрутка к выбранному разделу

🔐 Авторизация (4 способа)

Проверяется вход пользователя через:

кнопку «Войти в аккаунт»

кнопку «Личный кабинет»

страницу регистрации («Войти»)

страницу восстановления пароля

📝 Регистрация

успешная регистрация нового пользователя

ошибка при регистрации с коротким паролем

👤 Личный кабинет

переход в профиль из разных мест приложения

возврат в конструктор по кнопке и по логотипу

выход пользователя из аккаунта

Запуск тестов

Через Maven:

mvn clean test

Особенности реализации

Чёткая архитектура Page Object

Ожидания через WebDriverWait

Поддержка Chrome и Yandex Browser через BrowserFactory

API-клиент для:

авторизации тестовых пользователей

удаления созданных пользователей после тестов

Все тесты проходят стабильно (Maven: BUILD SUCCESS).