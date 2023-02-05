package requests;

import dto.booking.Booking;
import dto.token.CreateToken;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class Requests extends BaseRequest {
    public Requests(String token) {
        super(token);
    }

    public Requests() {
    }

    private static final String BOOKING = "booking";
    private static final String AUTH = "auth";
    private static final String BOOKING_BY_ID = "booking/{id}";
    public static final String USER_NAME = "admin";
    public static final String PASSWORD = "password123";

    public ValidatableResponse getBookingById(String id) {
        return given()
                .param("id", id)
                .when()
                .log()
                .everything()
                .when()
                .get(buildUrl(BOOKING_BY_ID))
                .then()
                .log()
                .everything();
    }

    public ValidatableResponse getBookingIds() {
        return given()
                .spec(getRequestSpecification())
                .when()
                .log()
                .everything()
                .when()
                .get(buildUrl(BOOKING))
                .then()
                .log()
                .everything();
    }

    public ValidatableResponse getBookingIds(String firstName, String lastName) {
        return given()
                .spec(getRequestSpecification())
                .queryParam("firstname", firstName)
                .queryParam("lastname", lastName)
                .when()
                .log()
                .everything()
                .when()
                .get(buildUrl(BOOKING))
                .then()
                .log()
                .everything();
    }

    public ValidatableResponse createToken(CreateToken body) {
        return given()
                .spec(getRequestSpecification())
                .body(body)
                .when()
                .log()
                .everything()
                .post(buildUrl(AUTH))
                .then()
                .log()
                .everything();
    }

    public ValidatableResponse createBooking(Booking body) {
        return given()
                .spec(getRequestSpecification())
                .body(body)
                .when()
                .log()
                .everything()
                .post(buildUrl(BOOKING))
                .then()
                .log()
                .everything();
    }

    public ValidatableResponse partialUpdateBooking(Integer id, Booking body) {
        return given()
                .spec(getRequestSpecification())
                .auth().basic(USER_NAME, PASSWORD)
                .body(body)
                .when()
                .log()
                .everything()
                .when()
                .patch(buildUrl(BOOKING_BY_ID), id)
                .then()
                .log()
                .everything();
    }

    public ValidatableResponse deleteBooking(Integer id) {
        return given()
                .spec(getRequestSpecification())
                .when()
                .log()
                .everything()
                .when()
                .delete(buildUrl(BOOKING_BY_ID), id)
                .then()
                .log()
                .everything();
    }
}
