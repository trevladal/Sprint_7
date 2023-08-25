import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
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
    @DisplayName("Creating courier without password")
    @Description("Checking for failure when creating a courier without a password")
    public void checkFailureCreatingCourierWithoutPass() {

        Response responseNewCourierWithoutPass = createCourier(login, "", firstName);

        responseNewCourierWithoutPass
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Creating courier without login")
    @Description("Checking for failure when creating a courier without a login")
    public void checkFailureCreatingCourierWithoutLogin() {

        Response responseNewCourierWithoutLogin = createCourier("", password, firstName);

        responseNewCourierWithoutLogin
                .then()
                .statusCode(400);
    }

    @Step("Creating courier")
    private Response createCourier(String login, String password, String firstName) {
        return courierAPI.creatingCourier(login, password, firstName);
    }
}

