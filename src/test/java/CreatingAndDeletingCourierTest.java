import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
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
    @DisplayName("Successful creating courier")
    @Description("Verifying successful courier creation")
    public void checkSuccessfulCreatingCourier() {

        Response response = creatingCourier();

        response
                .then()
                .assertThat()
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Check conflict username")
    @Description("Checking for username conflict when creating a courier")
    public void checkConflictUserName() {

        Response responseNewCourier = creatingCourier();

        responseNewCourier
                .then()
                .statusCode(201);

        Response responseFailureRecreatingCourier = creatingCourier();

        responseFailureRecreatingCourier
                .then()
                .statusCode(409);
    }

    @Test
    @DisplayName("Deleting courier")
    @Description("Verifying successful removal of the courier")
    public void deletingCourier() {
        creatingCourier();

        courierAPI.deleteCourier(login, password, firstName).then().statusCode(200);
    }

    @Step("Creating courier")
    private Response creatingCourier() {
        return courierAPI.creatingCourier(login, password, firstName);
    }

    @After
    public void tearDown() {
        courierAPI.deleteCourier(login, password, firstName);
    }
}