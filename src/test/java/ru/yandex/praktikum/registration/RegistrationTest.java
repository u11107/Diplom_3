package ru.yandex.praktikum.registration;

import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.base.Base;
import ru.yandex.praktikum.driver.DriverInitialization;
import ru.yandex.praktikum.util.GenerateData;
import ru.yandex.praktikum.url.Url;
import ru.yandex.praktikum.api.User;
import ru.yandex.praktikum.page.RegistrationPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

@Feature("Регистрация")
public class RegistrationTest extends Base {

    private String name;

    @Before
    public void setUp() {
        new DriverInitialization().startBrowser();
        user = GenerateData.generateUserAccount();
        registrationPage = open(Url.urlRegistration, RegistrationPage.class);
        name = user.getName();
        email = user.getEmail();
        password = user.getPassword();
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void successRegistrationTest() {
        registrationPage
                .inputNameEmailPasswordAndRegister(name, email, password);

        assertTrue(registrationPage.returnTrueIfRegistrationSuccess());
    }

    @Test
    @DisplayName("Проверка ошибки что пароль должен быть больше шести знаков")
    public void returnErrorIfShortPasswordTest() {
        registrationPage
                .inputNameEmailPasswordAndRegister(name, email, "123456");

        assertTrue(registrationPage.returnTrueIfShowShortPasswordError());

    }

    @After
    public void tearDown() {
        closeWebDriver();
        GenerateData.deleteUserAccount(user);
    }
}
