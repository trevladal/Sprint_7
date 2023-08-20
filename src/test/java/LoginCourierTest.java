import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sprint_7_classes.CourierAPI;
import sprint_7_classes.CourierID;

import static org.junit.Assert.assertNotNull;

public class LoginCourierTest extends AbstractCourierData {

    private final CourierAPI courierAPI = new CourierAPI();

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URI;

    }

    @Test
    public void checkSuccessfulCourierAuthorization() {

        courierAPI.creatingCourier(login, password, firstName);

        Response response = courierAPI.loginCourier(login, password, firstName);
        CourierID courierID = response.body().as(CourierID.class);
        assertNotNull(courierID.getId());

    }

    @Test
    public void checkFailureCourierAuthorization() {


        courierAPI.creatingCourier(login, password, firstName);

        Response responseAuthorizationWithoutPass =
                courierAPI.loginCourier(login, "", "");
        responseAuthorizationWithoutPass.then().statusCode(400);

        Response responseAuthorizationWithoutLogin =
                courierAPI.loginCourier("", password, "");
        responseAuthorizationWithoutLogin.then().statusCode(400);

        Response responseAuthorizationWithWrongPassword =
                courierAPI.loginCourier(login, "gdsft4353", "");
        responseAuthorizationWithWrongPassword.then().statusCode(404);

        Response responseAuthorizationWithWrongLogin =
                courierAPI.loginCourier("hgrer56y", password, "");
        responseAuthorizationWithWrongLogin.then().statusCode(404);

    }

    @After
    public void tearDown() {
        courierAPI.deleteCourier(login, password, firstName);
    }

}
