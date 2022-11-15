package ru.yandex.praktikum.logOut;

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
import ru.yandex.praktikum.api.User;
import ru.yandex.praktikum.page.GeneralPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

@Feature("Выход из аккаунта")
public class LogOutTest extends Base {


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
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    public void successLoginFromMainPageButtonLogInTest() {
        boolean expected = generalPage.openLoginPage()
                .inputEmailPasswordAndLogIn(email, password)
                .clickHeaderAccountButton()
                .clickLogOutButton()
                .returnTrueIfOpenLogInPage();

        assertTrue(expected);
    }

    @After
    public void tearDown() {
        closeWebDriver();
        GenerateData.deleteUserAccount(user);
    }
}
