import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CourierFailureCreateTest extends AbstractCreatingCourierTest {


    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
    }
    @Test
    public void checkFailureCreatingCourierWithoutFields() {
        Courier courierWithoutPassword = new Courier(login, "", "");

        given()
                .header("Content-type", "application/json")
                .body(courierWithoutPassword)
                .post(COURIER_PATH)
                .then()
                .statusCode(400);

        Courier courierWithoutLogin = new Courier("", password, "");
        given()
                .header("Content-type", "application/json")
                .body(courierWithoutLogin)
                .post(COURIER_PATH)
                .then()
                .statusCode(400);
    }
}
