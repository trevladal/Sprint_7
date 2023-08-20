import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import sprint_7_classes.CourierAPI;

public class CourierFailureCreateTest extends AbstractCourierData {
    private final CourierAPI courierAPI = new CourierAPI();

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

