# Диплом. Задание 3 — веб-приложение Stellar Burgers

Автоматизированные UI-тесты для веб-приложения https://stellarburgers.education-services.ru/.
Покрываются основные пользовательские сценарии: конструктор бургера, авторизация, регистрация и личный кабинет.

---

## 🧰 Технологический стек

- Java 11
- Maven
- JUnit 4
- Selenium WebDriver 4.7.2
- Chrome / Yandex Browser
- Page Object

---

## 📁 Структура проекта

Diplom_3/
pom.xml
README.md

src/
main/
java/
org/example/
browser/
BrowserFactory.java         # фабрика WebDriver
pages/
MainPage.java               # главная страница
LoginPage.java              # логин
RegisterPage.java           # регистрация
ForgotPasswordPage.java     # восстановление пароля
ProfilePage.java            # личный кабинет

    test/
      java/
        org/example/
          BaseTest.java                 # базовый класс
          ConstructorTabsTest.java      # тесты конструктора
          LoginTest.java                # тесты авторизации
          PersonalAccountTest.java      # личный кабинет
          RegisterTest.java             # тесты регистрации

---

## ✅ Покрытие тестами

### 🔧 Конструктор бургера
- переключение вкладок
- подсветка активной вкладки
- прокрутка к разделу

### 🔐 Авторизация (4 способа)
- кнопка «Войти в аккаунт»
- кнопка «Личный кабинет»
- страница регистрации
- страница восстановления пароля

### 📝 Регистрация
- успешная регистрация
- ошибка при коротком пароле

### 👤 Личный кабинет
- переход в профиль из разных частей
- переход в конструктор по кнопке и логотипу
- выход из аккаунта

---

## 🚀 Запуск тестов

Через Maven:
mvn clean test

Через IntelliJ IDEA:
ПКМ по директории test → Run 'All Tests'

---

