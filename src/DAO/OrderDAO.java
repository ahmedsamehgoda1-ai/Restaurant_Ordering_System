package DAO;
import MODEL.Order;
import MODEL.OrderItem;
import db.DBConnectionManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import static java.sql.DriverManager.getConnection;
public class OrderDAO{
    private final Connection con = DBConnectionManager.getConnection();

    public OrderDAO() throws SQLException {
    }

    public void insertOrder(Order order) throws Exception {

    String query1 = "insert into orders (id, totalprice) values(?,?)";
    String query2 = "insert into orderitems (order_id, order_price, order_quantity) values(?,?,?)";
    Class.forName("com.mysql.jdbc.Driver");
    PreparedStatement pst = con.prepareStatement(query1);
    pst.setInt(1, order.getid());
    pst.setDouble(2, order.getTotalPrice());
    PreparedStatement pst2 = con.prepareStatement(query2);
    for(OrderItem orderitem:order.getOrderItemList()) {
            pst2.setInt(1, order.getid());
            pst2.setDouble(2, orderitem.getPrice());
            pst2.setInt(3, orderitem.getQuantity());
            pst2.addBatch();
        }
        try {
            pst.executeUpdate();
            pst2.executeBatch();
            con.commit();
            System.out.println("Order inserted successfully");
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            con.close(); // always close connection
        }


}
}
