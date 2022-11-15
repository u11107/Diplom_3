package ru.yandex.praktikum.base;

import ru.yandex.praktikum.api.User;
import ru.yandex.praktikum.page.GeneralPage;
import ru.yandex.praktikum.page.LoginPage;
import ru.yandex.praktikum.page.RegistrationPage;

public class Base {

    protected User user;
    protected GeneralPage generalPage;
    protected String email;
    protected String password;

    protected RegistrationPage registrationPage;
    protected LoginPage loginPage;
}
