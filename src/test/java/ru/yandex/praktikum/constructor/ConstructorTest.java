package ru.yandex.praktikum.constructor;

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

@Feature("«Конструктор»")
public class ConstructorTest extends Base {


    @Before
    public void setUp() {
        new DriverInitialization().startBrowser();
        generalPage = open(Url.urlBase, GeneralPage.class);
    }

    @Test
    @DisplayName("Переходы к разделу Булки")
    public void openSectionBunTest() {
        boolean expected = generalPage
                .clickConstructorBunButton()
                .returnTrueIfBunSectionIsDisplayed();

        assertTrue(expected);
    }

    @Test
    @DisplayName("Переходы к разделу Соусы")
    public void openSectionSaucesTest() {
        boolean expected = generalPage
                .clickConstructorSaucesButton()
                .returnTrueIfSaucesSectionIsDisplayed();

        assertTrue(expected);
    }

    @Test
    @DisplayName("Переходы к разделу Начинки")
    public void openSectionFillingTest() {
        boolean expected = generalPage
                .clickConstructorFillingButton()
                .returnTrueIfFillingSectionIsDisplayed();

        assertTrue(expected);
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }
}
