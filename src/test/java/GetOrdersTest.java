import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sprint_7_classes.OrderAPI;
import sprint_7_classes.OrderList;
import sprint_7_classes.OrderTrack;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
    @DisplayName("Get orders")
    @Description("Check that the order and the list of orders are returned and not empty")
    public void getOrdersTest() {

        Response response = createOrder();

        response
                .then()
                .statusCode(201);

        orderTrack = getOrderTrack(response);

        getOrder()
                .then()
                .statusCode(200);

        Response responseAllOrders = getAllOrders();

        responseAllOrders
                .then()
                .statusCode(200);

        OrderList orders = getOrdersAsOrderList(responseAllOrders);
        assertThat(orders.isNotEmpty(), is(true));

    }

    @Step("Get all orders as OrderList object")
    private static OrderList getOrdersAsOrderList(Response responseAllOrders) {
        return responseAllOrders.body().as(OrderList.class);
    }

    @Step("Get all orders")
    private Response getAllOrders() {
        return orderAPI.getAllOrders();
    }

    @Step("Get order")
    private Response getOrder() {
        return orderAPI.getOrder(orderTrack);
    }

    @Step("Get order track")
    private static OrderTrack getOrderTrack(Response response) {
        return response.body().as(OrderTrack.class);
    }

    @Step("Creating order")
    private Response createOrder() {
        return orderAPI.creatingOrder(firstName, lastName, address, metroStation,
                phone, rentTime, deliveryDate, comment, color);
    }

    @After
    public void tearDown() {
        Response responseCancel = orderAPI.cancelOrder(orderTrack);

        responseCancel
                .then()
                .statusCode(200);
    }

}
