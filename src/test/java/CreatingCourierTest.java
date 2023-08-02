import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class CreatingCourierTest extends AbstractCreatingCourierTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
    }

    @Test
    public void checkSuccessfulCreatingCourier() {

        Courier courier = new Courier(login, password, firstName);
        given()
                .header("Content-type", "application/json")
                .body(courier)
                .post(COURIER_PATH)
                .then()
                .assertThat()
                .body("ok", equalTo(true));

    }

    @Test
    public void checkConflictUserName() {
        Courier courier = new Courier(login, password, firstName);

        given()
                .header("Content-type", "application/json")
                .body(courier)
                .post(COURIER_PATH)
                .then()
                .statusCode(201);


        given()
                .header("Content-type", "application/json")
                .body(courier)
                .post(COURIER_PATH)
                .then()
                .statusCode(409);

    }

}