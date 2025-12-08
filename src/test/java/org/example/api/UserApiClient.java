package org.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApiClient {

    // Базовый URL API Stellar Burgers
    private static final String BASE_URI = "https://stellarburgers.education-services.ru";

    public UserApiClient() {
        RestAssured.baseURI = BASE_URI;
    }

    /**
     * Логинимся по email+password и возвращаем accessToken.
     * Если логин неудачный — вернёт null.
     */
    public String loginAndGetToken(String email, String password) {
        try {
            Response response =
                    given()
                            .header("Content-type", "application/json")
                            .body("{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}")
                            .when()
                            .post("/api/auth/login");

            if (response.statusCode() != 200) {
                return null;
            }

            // В ответе Practicum обычно присылает "accessToken": "Bearer <токен>"
            return response.path("accessToken");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Удаление пользователя по токену.
     * Ошибки игнорируем, чтобы не заваливать тесты из-за cleanup-а.
     */
    public void deleteUser(String token) {
        if (token == null || token.isEmpty()) {
            return;
        }

        try {
            given()
                    .header("Authorization", token)
                    .when()
                    .delete("/api/auth/user");
            // без .then() — не валим тест, если код ответа неожиданный
        } catch (Exception ignored) {
        }
    }
}










