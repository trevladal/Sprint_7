import org.junit.After;

import static io.restassured.RestAssured.given;

public abstract class AbstractCreatingCourierTest {
    public final static String BASE_URI = "https://qa-scooter.praktikum-services.ru";
    protected final static String COURIER_PATH = "/api/v1/courier";
    protected final static String COURIER_LOGIN_PATH = COURIER_PATH + "/login";
    protected final String login = "aboba";
    protected final String password = "1234";
    protected final String firstName = "amogus";


    @After
    public void tearDown() {
        String json = "{ \"login\": \"" + login + "\", \"password\": \"" + password + "\"}";


        CourierID courierID = given()
                .header("Content-type", "application/json")
                .body(json)
                .post(COURIER_LOGIN_PATH)
                .body().as(CourierID.class);
        if (courierID.getId() != null) {
            given()
                    .header("Content-type", "application/json")
                    .body(courierID)
                    .delete(COURIER_PATH + "/" + courierID.getId()).then().statusCode(200);
        }
    }
}
