package SERVICES;
import DAO.OrderDAO;
import MODEL.Order;
import MODEL.OrderItem;

import java.sql.SQLException;

public class OrderService {
    private final OrderDAO dao=new OrderDAO();

    public OrderService() throws SQLException {
    }

    public void placeOrder(Order order){
        try{
            dao.insertOrder(order);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
