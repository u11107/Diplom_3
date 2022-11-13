package ru.yandex.praktikum.account;

import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.base.Base;
import ru.yandex.praktikum.driver.DriverInitialization;
import ru.yandex.praktikum.url.Url;
import ru.yandex.praktikum.page.GeneralPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

@Feature("Личный кабинет")
public class PersonalAccountTest extends Base {

    @Before
    public void setUp() {
        new DriverInitialization().startBrowser();
        generalPage = open(Url.urlBase, GeneralPage.class);
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет».")
    public void openPersonalAccountTest() {
        boolean expected = generalPage.clickHeaderAccountButton()
                .returnTrueIfOpenLogInPage();

        assertTrue(expected);
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }
}
