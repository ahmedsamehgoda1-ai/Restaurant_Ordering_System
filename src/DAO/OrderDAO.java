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

    String query1 = "insert into orders (totalprice) values(?)";
    String query2 = "insert into orderitems (order_id, order_price, order_quantity) values(?,?,?)";

        try {
            PreparedStatement pst = con.prepareStatement(query1,Statement.RETURN_GENERATED_KEYS);
            pst.setDouble(1, order.getTotalPrice());
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            rs.next();
            int orderId = rs.getInt(1);
            PreparedStatement pst2 = con.prepareStatement(query2);
            for(OrderItem orderitem:order.getOrderItemList()) {
                pst2.setInt(1, orderId);
                pst2.setDouble(2, orderitem.getPrice());
                pst2.setInt(3, orderitem.getQuantity());
                pst2.addBatch();
            }

            pst2.executeBatch();
            System.out.println("Order inserted successfully");
        } catch (SQLException e) {
            throw e;
        } finally {
            con.close();
        }



}
}
