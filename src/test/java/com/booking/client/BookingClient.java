package com.booking.client;

import com.booking.config.ApiConfig;
import com.booking.model.Booking;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingClient {

    public Response createBooking(String token, Booking booking) {
        return given()
                .baseUri(ApiConfig.baseUrl())
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .body(booking)
                .when()
                .post("/booking")
                .then()
                .extract()
                .response();
    }

    public Response getBooking(String token, int bookingId) {
        return given()
                .baseUri(ApiConfig.baseUrl())
                .cookie("token", token)
                .when()
                .get("/booking/{id}", bookingId)
                .then()
                .extract()
                .response();
    }
}