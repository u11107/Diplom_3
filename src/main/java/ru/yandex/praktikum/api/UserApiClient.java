package ru.yandex.praktikum.api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserApiClient extends RestAssuredClient {

    private String bearerToken = "";

    @Step("АПИ Авторизация")
    public Response authorization(User body) {
        Response response = reqSpec
                .contentType(ContentType.JSON)
                .body(body)
                .post("/auth/login");
        extractToken(response);
        return response;
    }

    @Step("АПИ Создание пользователя")
    public Response createUser(User json) {
        Response response = reqSpec
                .contentType(ContentType.JSON)
                .body(json)
                .post("/auth/register");
        extractToken(response);
        return response;
    }

    @Step("АПИ Очистка токена")
    public void clearAuthToken() {
        bearerToken = "";
    }

    @Step("АПИ получение токена")
    private void extractToken(Response response) {
        if (response.statusCode() == 200) {
            bearerToken = response.then().extract().body().path("accessToken");
        } else {
            clearAuthToken();
        }
    }

    @Step("АПИ Пользователь удалён")
    public void deleteUser() {
        reqSpec.header("Authorization", bearerToken)
                .delete("/auth/user");
    }
}