package ru.yandex.praktikum.authorization;

import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.base.Base;
import ru.yandex.praktikum.driver.DriverInitialization;
import ru.yandex.praktikum.util.GenerateData;
import ru.yandex.praktikum.url.Url;
import ru.yandex.praktikum.api.UserApiClient;
import ru.yandex.praktikum.page.GeneralPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

@Feature("Авторизация")
public class UserAuthorizationTest extends Base {

    @Before
    public void setUp() {
        new DriverInitialization().startBrowser();
        UserApiClient userApiClient = new UserApiClient();
        user = GenerateData.generateUserAccount();
        userApiClient.createUser(user);
        generalPage = open(Url.urlBase, GeneralPage.class);
        email = user.getEmail();
        password = user.getPassword();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void successLoginFromMainPageButtonLogInTest() {
        generalPage.clickMainLogInButton()
                .inputEmailPasswordAndLogIn(email, password);

        assertTrue(generalPage.returnTrueIfCreateOrderButtonExist());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void successLoginFromMainPageHeaderButtonAccountTest() {
        generalPage.clickHeaderAccountButton()
                .inputEmailPasswordAndLogIn(email, password);

        assertTrue(generalPage.returnTrueIfCreateOrderButtonExist());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void successLoginFromRegistrationPageAccountTest() {
        generalPage.openRegisterPage()
                .clickHyperLinkLogIn()
                .inputEmailPasswordAndLogIn(email, password);

        assertTrue(generalPage.returnTrueIfCreateOrderButtonExist());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void successLoginFromRestorePasswordTest() {
        generalPage.openRestorePasswordPage()
                .clickHyperLogIn()
                .inputEmailPasswordAndLogIn(email, password);

        assertTrue(generalPage.returnTrueIfCreateOrderButtonExist());
    }

    @After
    public void tearDown() {
        closeWebDriver();
        GenerateData.deleteUserAccount(user);
    }
}
