package SERVICES;
import DAO.OrderDAO;
import MODEL.Order;
import MODEL.OrderItem;
public class OrderService {
    private final OrderDAO dao=new OrderDAO();
    public void placeOrder(Order order){
        try{
            dao.insertOrder(order);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
