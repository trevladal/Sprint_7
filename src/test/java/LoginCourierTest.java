import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sprint_7_classes.CourierAPI;
import sprint_7_classes.CourierID;

import static org.junit.Assert.assertNotNull;

public class LoginCourierTest {

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
    @DisplayName("Check successful courier authorization")
    @Description("Checking that the courier can log in and ID not empty")
    public void checkSuccessfulCourierAuthorization() {

        createCourier();

        Response response = authorization(login, password, 200);
        CourierID courierID = response.body().as(CourierID.class);
        assertNotNull(courierID.getId());

    }

    @Test
    @DisplayName("Check failure courier authorization")
    @Description("Checking that the courier cannot enter with incorrect and empty data")
    public void checkFailureCourierAuthorization() {

        createCourier();

        authorization(login, "", 400);

        authorization("", password, 400);

        authorization(login, "gdsft4353", 404);

        authorization("hgrer56y", password, 404);

    }
    @Step("Authorization courier")
    private Response authorization(String login, String password, int statusCode) {
        Response responseAuthorization =
                courierAPI.loginCourier(login, password, "");
        responseAuthorization.then().statusCode(statusCode);
        return responseAuthorization;
    }

    @Step("Creating courier")
    private void createCourier() {
        courierAPI.creatingCourier(login, password, firstName);
    }

    @After
    public void tearDown() {
        courierAPI.deleteCourier(login, password, firstName);
    }

}
