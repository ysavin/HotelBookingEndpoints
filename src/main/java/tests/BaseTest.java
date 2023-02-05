package tests;

import dto.token.CreateToken;
import requests.HotelBookingBehavior;

public class BaseTest {

    public BaseTest() {
        login();
    }

    protected String token;
    protected HotelBookingBehavior bookingBehavior = new HotelBookingBehavior();

    public static final String USER_NAME = "admin";
    public static final String PASSWORD = "password123";

    public void login() {
        token = bookingBehavior.createToken(prepareTokenBody()).getToken();
    }

    public CreateToken prepareTokenBody() {
        CreateToken body = new CreateToken();
        body.setUserName(USER_NAME);
        body.setPassword(PASSWORD);
        return body;
    }
}
