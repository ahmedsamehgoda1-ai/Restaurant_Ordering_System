package MODEL;
public class OrderItem {
    private Menu menuitem;
    private int quantity;
    public OrderItem(Menu menuitem, int quantity) {
        this.menuitem = menuitem;
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return this.menuitem.getPrice()*this.quantity;
    }

}

