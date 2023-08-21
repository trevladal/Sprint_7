package sprint_7_classes;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderAPI {

    public Response creatingOrder (String firstName, String lastName, String address, String metroStation,
                                   String phone, int rentTime, String deliveryDate, String comment, String[] color) {
       Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);

        return given()
                .header("Content-type", "application/json")
                .body(order)
                .post("/api/v1/orders");
    }

    public Response getOrder (OrderTrack orderTrack) {
        return given()
                .header("Content-type", "application/json")
                .get("/api/v1/orders?t=" + orderTrack.getTrack());
    }

    public Response getAllOrders () {
        return given()
                .header("Content-type", "application/json")
                .get("/api/v1/orders");
    }

    public Response cancelOrder (OrderTrack orderTrack) {
        return given()
                .header("Content-type", "application/json")
                .body(orderTrack)
                .put("/api/v1/orders/cancel?track=" + orderTrack.getTrack());
    }
}
