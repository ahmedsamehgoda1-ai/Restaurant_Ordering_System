package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionManager {
    private static Properties props;

    static {
        props = new Properties();
        try {
            props.load(new FileInputStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
     static final String URL = props.getProperty("URL");
     static final String username = props.getProperty("USERNAME");
     static final String password = props.getProperty("PASSWORD");

    public String getURL() {
        return URL;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,username,password);

    }
}
