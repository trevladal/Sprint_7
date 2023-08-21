import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sprint_7_classes.OrderAPI;
import sprint_7_classes.OrderList;
import sprint_7_classes.OrderTrack;

import static io.restassured.RestAssured.given;

public class GetOrdersTest {


    private final  OrderAPI orderAPI = new OrderAPI();
    private final String firstName = "Орочимару";
    private final String lastName = "Легенда";
    private final String address = "Пещера";
    private final String metroStation = "1";
    private final String phone = "8800111111";
    private final int rentTime = 3;
    private final String deliveryDate = "2025-03-08";
    private final String comment = "Pssss";
    private final String[] color = {"BLACK"};
    private OrderTrack orderTrack;
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void getOrdersTest() {

        Response response = orderAPI.creatingOrder(firstName, lastName, address, metroStation,
                phone, rentTime, deliveryDate, comment, color);

        response
                .then()
                .statusCode(201);

        orderTrack = response.body().as(OrderTrack.class);

        orderAPI.getOrder(orderTrack).then().statusCode(200);

        //получение всех заказов

        Response responseAllOrders = orderAPI.getAllOrders();

        responseAllOrders
                .then()
                .statusCode(200);

        response.body().as(OrderList.class);
    }

    @After
    public void tearDown() {
        Response responseCancel = orderAPI.cancelOrder(orderTrack);

        responseCancel.then().statusCode(200);
    }

}
