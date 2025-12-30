package DAO;
import MODEL.Order;
import MODEL.OrderItem;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import static java.sql.DriverManager.getConnection;
public class OrderDAO{
    private static Properties props;
    static {
        props = new Properties();
        try {
            props.load(new FileInputStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private final String URL = props.getProperty("URL");
    private final String username = props.getProperty("USERNAME");
    private final String password = props.getProperty("PASSWORD");
public void insertOrder(Order order) throws Exception {

    String query1 = "insert into orders (id, totalprice) values(?,?)";
    String query2 = "insert into orderitems (order_id, order_price, order_quantity) values(?,?,?)";
    Class.forName("com.mysql.jdbc.Driver");
    try (Connection con = getConnection(URL, username, password);
         PreparedStatement pst = con.prepareStatement(query1)) {
        pst.setInt(1, order.getid());
        pst.setDouble(2, order.getTotalPrice());
        pst.executeUpdate();
    }catch (Exception e){
        throw e;
    }

    try (Connection con = getConnection(URL, username, password);
         PreparedStatement pst = con.prepareStatement(query2)) {
        for(OrderItem orderitem:order.getOrderItemList()) {
            pst.setInt(1, order.getid());
            pst.setDouble(2, orderitem.getPrice());
            pst.setInt(3, orderitem.getQuantity());
            pst.addBatch();
        }
        pst.executeBatch();
        System.out.println("Order inserted successfully");
    }catch (Exception e){
        throw e;
    }
}
}