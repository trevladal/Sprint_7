import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetOrdersTest {

    private String firstName = "Орочимару";
    private String lastName = "Орочимарский";
    private String address = "Пещера";
    private String metroStation = "1";
    private String phone = "8800111111";
    private int rentTime = 3;
    private String deliveryDate = "2025-03-08";
    private String comment = "Pssss";
    private String[] color = {"BLACK"};


    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void getOrdersTest() {

        Order order = new Order(firstName, lastName, address, metroStation,
                phone, rentTime, deliveryDate, comment, color);
        //создание заказа
        Response responseOrder =
                given()
                        .header("Content-type", "application/json")
                        .body(order)
                        .post("/api/v1/orders");
        responseOrder
                .then()
                .statusCode(201);

        OrderTrack orderTrack = responseOrder.body().as(OrderTrack.class);
        //получение созданного заказа
        given()
                .header("Content-type", "application/json")
                .get("/api/v1/orders?t=" + orderTrack.getTrack()).then().statusCode(200);

        //получение всех заказов

        Response response = given()
                .header("Content-type", "application/json")
                .get("/api/v1/orders");

        response
                .then()
                .statusCode(200);


        response.body().as(OrderList.class);


        //удаление заказа

        given()
                .header("Content-type", "application/json")
                .body(orderTrack)
                .put("/api/v1/orders/cancel?track=" + orderTrack.getTrack())
                .then()
                .statusCode(200);

    }

}
