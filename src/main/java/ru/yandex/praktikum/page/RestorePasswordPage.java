package ru.yandex.praktikum.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class RestorePasswordPage extends GeneralPage {
    //Поле Email.
    @FindBy(how = How.XPATH, using = ".//label[text()='Email']/parent::div//input")
    private SelenideElement inputEmailRestorePage;

    //Кнопка Восстановить
    @FindBy(how = How.XPATH, using = ".//button[text()='Восстановить']")
    private SelenideElement restoreButton;

    //Гиперссылка Войти
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement hyperLinkLogIn;

    public RestorePasswordPage inputEmailForRestore(String email) {
        inputEmailRestorePage.shouldBe(visible);
        inputEmailRestorePage.setValue(email);
        return this;
    }

    public void clickRestoreButton() {
        restoreButton.click();
    }

    @Step("Нажата гиперссылка Войти")
    public LoginPage clickHyperLogIn() {
        hyperLinkLogIn.click();
        return page(LoginPage.class);
    }

}
