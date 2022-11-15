package ru.yandex.praktikum.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;

public class RestAssuredClient {
    protected static String BASE_URL = "https://stellarburgers.nomoreparties.site/api/";
    protected Filter request = new RequestLoggingFilter();
    protected Filter response = new ResponseLoggingFilter();
    protected RequestSpecification reqSpec = with()
            .filters(request, response)
            .filter(new AllureRestAssured())
            .baseUri(BASE_URL);
}
