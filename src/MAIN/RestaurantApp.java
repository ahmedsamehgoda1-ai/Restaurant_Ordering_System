package MAIN;
import DAO.MenuDAO;
import MODEL.*;
import SERVICES.OrderService;
import java.util.*;
public class RestaurantApp {
    public static void main(String[] args) throws Exception {
        MenuDAO menuDAO = new MenuDAO();
        OrderService orderService = new OrderService();

        Scanner sc = new Scanner(System.in);
        System.out.println("Loading menu...");
        List<Menu> menuItems = menuDAO.getMenuItems();

        System.out.println("==== MENU ====");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println((i + 1) + ". " + menuItems.get(i).getName() + " - $" + menuItems.get(i).getPrice());
        }

        Order order = new Order((int) (Math.random() * 10000));

        while (true) {
            System.out.print("\nEnter item number to add (0 to finish): ");
            int choice = sc.nextInt();
            if (choice == 0) break;

            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();

            Menu selected = menuItems.get(choice - 1);
            order.addItem(new OrderItem(selected, qty));
        }

        System.out.println("Your total: $" + order.getTotalPrice());
        orderService.placeOrder(order);

        System.out.println("✅ Order saved in database. Thank you!");
    }
}
