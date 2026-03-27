package com.booking.client;

import com.booking.config.ApiConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthClient {

    public String login(String username, String password) {
        Response response = given()
                .baseUri(ApiConfig.baseUrl())
                .contentType(ContentType.JSON)
                .body(Map.of(
                        "username", username,
                        "password", password
                ))
                .when()
                .post("/auth/login")
                .then()
                .extract()
                .response();

        return response.getDetailedCookie("token").getValue();
    }
}