import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import sprint_7_classes.CourierAPI;

public class CourierFailureCreateTest {
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
    public void checkFailureCreatingCourierWithoutPass() {

        Response responseNewCourierWithoutPass = courierAPI.creatingCourier(login, "", firstName);

        responseNewCourierWithoutPass
                .then()
                .statusCode(400);
    }

    @Test
    public void checkFailureCreatingCourierWithoutLogin() {

        Response responseNewCourierWithoutLogin = courierAPI.creatingCourier("", password, firstName);

        responseNewCourierWithoutLogin
                .then()
                .statusCode(400);
    }

}

