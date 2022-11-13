package ru.yandex.praktikum.constructor;

import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.base.Base;
import ru.yandex.praktikum.driver.DriverInitialization;
import ru.yandex.praktikum.url.Url;
import ru.yandex.praktikum.page.LoginPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

@Feature("Переход из личного кабинета в конструктор\n")
public class ConstructorFromLogInPageTest extends Base {

    @Before
    public void setUp() {
        new DriverInitialization().startBrowser();
        loginPage = open(Url.urlLogin, LoginPage.class);
    }

    @Test
    @DisplayName("Переход по клику на «Конструктор»")
    public void openConstructorViaHeaderFromAuthPageTest() {
        loginPage.clickHeaderConstructorButton();

        assertTrue(loginPage.returnTrueIfOpenConstructor());
    }

    @Test
    @DisplayName("Переход по клику на логотип Stellar Burgers")
    public void openConstructorViaLogoFromAuthPageTest() {
        loginPage.clickHeaderLogo();

        assertTrue(loginPage.returnTrueIfOpenConstructor());
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }
}
