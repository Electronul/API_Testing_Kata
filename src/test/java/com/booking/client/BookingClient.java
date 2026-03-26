package com.booking.client;

import com.booking.config.ApiConfig;
import com.booking.model.Booking;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingClient {

    public Response createBooking(Booking booking) {
        return given()
                .baseUri(ApiConfig.baseUrl())
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking")
                .then()
                .extract()
                .response();
    }
}