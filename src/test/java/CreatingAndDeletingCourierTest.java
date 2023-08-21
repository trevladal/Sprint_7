import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sprint_7_classes.CourierAPI;

import static org.hamcrest.core.IsEqual.equalTo;

public class CreatingAndDeletingCourierTest {

    public final static String BASE_URI = "https://qa-scooter.praktikum-services.ru";
    private final CourierAPI courierAPI = new CourierAPI();
    private final String login = "aboba";
    private final String password = "1234";
    private final String firstName = "amogus";

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URI;

    }

    @Test
    public void checkSuccessfulCreatingCourier() {

        Response response = courierAPI.creatingCourier(login, password, firstName);

        response
                .then()
                .assertThat()
                .body("ok", equalTo(true));
    }

    @Test
    public void checkConflictUserName() {

        Response responseNewCourier = courierAPI.creatingCourier(login, password, firstName);

        responseNewCourier
                .then()
                .statusCode(201);

        Response responseFailureRecreatingCourier = courierAPI.creatingCourier(login, password, firstName);

        responseFailureRecreatingCourier
                .then()
                .statusCode(409);
    }

    @Test
    public void deletingCourier() {
        courierAPI.creatingCourier(login, password, firstName);

        courierAPI.deleteCourier(login, password, firstName).then().statusCode(200);
    }

    @After
    public void tearDown() {
        courierAPI.deleteCourier(login, password, firstName);
    }
}