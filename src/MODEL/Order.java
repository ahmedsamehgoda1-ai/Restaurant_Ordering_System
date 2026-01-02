package MODEL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class Order  {
    private List<OrderItem> orderItemList = new ArrayList<>();

    private double finalprice=0;
    private int id;
    public void addItem(OrderItem Item) {
        orderItemList.add(Item);
        finalprice=finalprice+Item.getPrice();
    }
    public Order(){

    }
    ///
    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }
    public  double getTotalPrice() {
        return finalprice;
    }
    public int getid() {
        return id;
    }

    ///

    }


