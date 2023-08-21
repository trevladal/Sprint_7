import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import sprint_7_classes.OrderAPI;
import sprint_7_classes.OrderTrack;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class CreatingOrderTest {
    public final static String BASE_URI = "https://qa-scooter.praktikum-services.ru";
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] color;
    private static final String[] blackColor = {"BLACK"};
    private static final String[] greyColor = {"GRAY"};
    private static final String[] twoColor = {"BLACK", "GRAY"};

    private OrderTrack orderTrack;
    private final OrderAPI orderAPI = new OrderAPI();

    public CreatingOrderTest(String firstName, String lastName, String address, String metroStation,
                             String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;

    }

    @Parameterized.Parameters
    public static Object[] getOrder() {
        return new Object[][]{
                {"Naruto", "Uchiha", "Konoha, 142 apt.", "4", "+7 800 355 35 35", 5, "2020-07-08",
                        "Sasuke, come back to Konoha", blackColor},
                {"Sasuke", "Uzumaki", "Konoha, 555 apt.", "3", "+7 800 355 35 35", 4, "2021-09-10",
                        "No", greyColor},
                {"Minato", "Namikaze", "Konoha, 556 apt.", "2", "+7 800 355 35 35", 3, "2022-08-11",
                        "Я верю, что придет время, когда люди смогут по-настоящему понять друг друга", twoColor},
                {"Hinata", "Hyuga", "Konoha, 7 apt.", "1", "+7 800 355 35 35", 2, "2023-05-12",
                        "В этот раз я спасу тебя, Наруто-кун", null}
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URI;

    }

    @Test
    public void creatingOrderTest() {

        Response response = orderAPI.creatingOrder(firstName, lastName, address, metroStation,
                phone, rentTime, deliveryDate, comment, color);

        response
                .then()
                .statusCode(201);

        orderTrack = response.body().as(OrderTrack.class);

        orderAPI.getOrder(orderTrack).then().statusCode(200);

        assertNotNull(orderTrack.getTrack());

    }

    @After
    public void tearDown() {
        Response responseCancel = orderAPI.cancelOrder(orderTrack);

        responseCancel.then().statusCode(200);
    }
}
