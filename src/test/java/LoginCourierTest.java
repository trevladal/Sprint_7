import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;

public class LoginCourierTest extends AbstractCreatingCourierTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
        Courier courier = new Courier(login, password, firstName);

        given()
                .header("Content-type", "application/json")
                .body(courier)
                .post(COURIER_PATH);

    }

    @Test
    public void checkSuccessfulCourierAuthorization() {

        CourierID courierID = login(login, password, 200);
        assertNotNull(courierID.getId());

    }

    @Test
    public void checkFailureCourierAuthorization() {

        login(login, "", 400);

        login("", password, 400);

        login(login, "gdsft4353", 404);

        login("hgrer56y", password, 404);

    }

    private static CourierID login(String login, String password, int statusCode) {

        String json = "{ \"login\": \"" + login + "\", \"password\": \"" + password + "\"}";


        Response response = given()
                .header("Content-type", "application/json")
                .body(json)
                .post(COURIER_LOGIN_PATH);

        response
                .then()
                .statusCode(statusCode);

        return response.body().as(CourierID.class);
    }


}
