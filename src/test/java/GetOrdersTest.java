import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sprint_7_classes.OrderList;
import sprint_7_classes.OrderTrack;

import static io.restassured.RestAssured.given;

public class GetOrdersTest extends AbstractOrderData {


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

        //получение созданного заказа
        given()
                .header("Content-type", "application/json")
                .get("/api/v1/orders?t=" + orderTrack.getTrack()).then().statusCode(200);

        //получение всех заказов

        Response responseAllOrders = given()
                .header("Content-type", "application/json")
                .get("/api/v1/orders");

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
