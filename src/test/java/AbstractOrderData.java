import sprint_7_classes.OrderAPI;
import sprint_7_classes.OrderTrack;

public abstract class AbstractOrderData {
    protected String firstName = "Орочимару";
    protected String lastName = "Легенда";
    protected String address = "Пещера";
    protected String metroStation = "1";
    protected String phone = "8800111111";
    protected int rentTime = 3;
    protected String deliveryDate = "2025-03-08";
    protected String comment = "Pssss";
    protected String[] color = {"BLACK"};
    protected OrderTrack orderTrack;
    protected final OrderAPI orderAPI = new OrderAPI();
}
