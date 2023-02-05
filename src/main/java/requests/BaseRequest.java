package requests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Cookie;
import io.restassured.specification.RequestSpecification;


public class BaseRequest {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com/";
    private RequestSpecification requestSpecification;
    private RequestSpecBuilder requestSpecBuilder;

    public static String buildUrl(String endpoint) {
        return BASE_URL + endpoint;
    }

    public BaseRequest() {
        requestSpecBuilder = new RequestSpecBuilder();
        setHeaders();
        setRequestSpecification();
    }

    public BaseRequest(String token) {
        requestSpecBuilder = new RequestSpecBuilder();
        setHeaders(token);
        setRequestSpecification();
    }

    private void setHeaders() {
        setRequestSpecBuilder(getRequestSpecBuilder()
                .addHeader("Content-type", "application/json")
        );
    }

    private void setHeaders(String token) {
        setRequestSpecBuilder(getRequestSpecBuilder()
                .setContentType("application/json")
                .addCookie(setCookie(token))
        );
    }

    private Cookie setCookie(String token) {
        return new Cookie.Builder("token", token).build();
    }

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    private void setRequestSpecBuilder(RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
    }

    private RequestSpecBuilder getRequestSpecBuilder() {
        return requestSpecBuilder;
    }

    private void setRequestSpecification() {
        requestSpecification = requestSpecBuilder.build();
    }

}
