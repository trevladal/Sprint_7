package sprint_7_classes;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierAPI {

    public Response creatingCourier(String login, String password, String firstName) {

        Courier courier = new Courier(login, password, firstName);

        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .post("/api/v1/courier");
    }

    public Response deleteCourier(String login, String password, String firstName) {

        Courier courier = new Courier(login, password, firstName);
        Response response = null;

        CourierID courierID = given()
                .header("Content-type", "application/json")
                .body(courier)
                .post("/api/v1/courier/login")
                .body().as(CourierID.class);
        if (courierID.getId() != null) {
            response = given()
                    .header("Content-type", "application/json")
                    .body(courierID)
                    .delete("/api/v1/courier" + "/" + courierID.getId());
        }
        return response;
    }

    public Response loginCourier(String login, String password, String firstName) {

        Courier courier = new Courier(login, password, firstName);



        return  given()
                .header("Content-type", "application/json")
                .body(courier)
                .post("/api/v1/courier/login");
    }
}
