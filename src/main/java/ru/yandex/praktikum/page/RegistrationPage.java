package ru.yandex.praktikum.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage extends GeneralPage {

    //Поле Имя.
    @FindBy(how = How.XPATH, using = ".//label[text()='Имя']/parent::div//input")
    private SelenideElement inputName;

    //Поле Email.
    @FindBy(how = How.XPATH, using = ".//label[text()='Email']/parent::div//input")
    private SelenideElement inputEmail;

    //Поле Пароль.
    @FindBy(how = How.XPATH, using = ".//label[text()='Пароль']/parent::div//input")
    private SelenideElement inputPassword;

    //Кнопка Зарегистрироваться
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement buttonRegister;

    //Ошибка короткого пароля
    @FindBy(how = How.XPATH, using = ".//p[text()='Некорректный пароль']")
    private SelenideElement errorPassword;

    //Страница Входа
    @FindBy(how = How.XPATH, using = ".//h2[text()='Вход']")
    private SelenideElement textOnLoginPage;

    //Гиперссылка Войти
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement hyperLinkLogIn;

    @Step("Ввод имени {name}")
    public RegistrationPage setRegName(String name) {
        inputName.setValue(name);
        return this;
    }

    @Step("Ввод почты {email}")
    public RegistrationPage setRegEmail(String email) {
        inputEmail.setValue(email);
        return this;
    }

    @Step("Ввод пароля {password}")
    public RegistrationPage setRegPassword(String password) {
        inputPassword.setValue(password);
        return this;
    }

    @Step("Нажата кнопка Регистрация")
    public RegistrationPage clickRegistrationButton() {
        buttonRegister.click();
        return this;
    }

    @Step("Нажата гиперссылка Войти")
    public LoginPage clickHyperLinkLogIn() {
        hyperLinkLogIn.click();
        return page(LoginPage.class);
    }

    @Step("Проверка что появилась ошибка")
    public boolean returnTrueIfShowShortPasswordError() {
        return errorPassword.exists();
    }

    public RegistrationPage inputNameEmailPasswordAndRegister(String name, String email, String password) {
        setRegName(name)
                .setRegEmail(email)
                .setRegPassword(password)
                .clickRegistrationButton();
        return this;
    }

    @Step("Проверка что регистрация прошла")
    public boolean returnTrueIfRegistrationSuccess() {
        return textOnLoginPage.shouldBe(Condition.visible).exists();
    }

}
