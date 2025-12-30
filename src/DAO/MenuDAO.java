package DAO;
import MODEL.Menu;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.*;
import java.util.Properties;

import static java.sql.DriverManager.getConnection;

public class MenuDAO {
    private static Properties props;

    static {
        props = new Properties();
        try {
            props.load(new FileInputStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Menu> menuItems = new ArrayList<>();
    private final String URL = props.getProperty("URL");
    private final String username = props.getProperty("USERNAME");
    private final String password = props.getProperty("PASSWORD");
    public MenuDAO() throws SQLException {
        menuItems.add(new Menu("NewYork pizza",16.50));
        menuItems.add(new Menu("Chicago pizza",18.00));
        menuItems.add(new Menu("Samurai pizza",15.00));
        menuItems.add(new Menu("SeaFood pizza",25.99));
        menuItems.add(new Menu("Quatro_Formagi pizza",11.99));
        menuItems.add(new Menu("MeatFest pizza",20.50));
        menuItems.add(new Menu("Vegan pizza",10.00));
        menuItems.add(new Menu("Soda",4.00));
        menuItems.add(new Menu("Juice",5.00));
    }
    public void InsertIntoDB() throws Exception {
        String query1="insert into menu_items (name, price) values(?,?)";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = getConnection(URL,username,password);
        PreparedStatement ps = con.prepareStatement(query1);
        for(Menu item:menuItems){
            ps.setString(1, item.getName());
            ps.setDouble(2, item.getPrice());
            ps.executeUpdate();
        }
    }
    public void showMenu() throws Exception{
        String query1="SELECT * from menu_items";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = getConnection(URL,username,password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query1);
        System.out.println("\n=== Menu ===");
        int index = 1;
        while(rs.next()){
            String name = rs.getString("name");
            double price = rs.getDouble("price");
            System.out.println(index+" - "+ name + " "+price);
            index++;
        }

    }
    public ArrayList<Menu> getMenuItems() {
        return menuItems;
    }
    public void deleteMenu() throws ClassNotFoundException, SQLException {
        String query1="TRUNCATE TABLE menu_items";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = getConnection(URL,username,password);
        Statement st = con.createStatement();
        st.executeUpdate(query1);
    }
}
