package ru.yandex.praktikum.util;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.praktikum.api.UserApiClient;
import ru.yandex.praktikum.api.User;

public class GenerateData {
    private static String email;
    private static String password;
    private static String name;
    private static final Faker faker = new Faker();

    private static void createUserData() {
        generateEmail();
        generatePassword();
        generateName();
    }

    @Step("Генерация данных пользователя")
    public static User generateUserAccount() {
        createUserData();
        return new User(email, password, name);
    }

    public static void generateEmail() {
        email = faker.internet().emailAddress();
    }

    public static void generatePassword() {
        password = faker.internet().password();
    }

    public static void generateName() {
        name = faker.name().username();
    }

    @Step("Удаление пользователя с почтой {userReqJson.email} и паролем {userReqJson.password}")
    public static void deleteUserAccount(User user) {
        UserApiClient userApiClient = new UserApiClient();
        Response responseAuth = userApiClient.authorization(user);
        if (responseAuth.statusCode() == 200) {
            userApiClient.deleteUser();
        } else {
            System.out.println("Пользователь создан не был");
        }
    }
}
