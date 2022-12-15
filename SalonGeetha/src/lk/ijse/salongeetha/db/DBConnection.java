package lk.ijse.salongeetha.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dBConnection;
    private Connection connection;
    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/SalonGeetha", "root", "1234");
    }
    public Connection getConnection(){
        return connection;
    }
    public static DBConnection getDBConnection() throws SQLException, ClassNotFoundException {
        if (dBConnection == null) {
            dBConnection=new DBConnection();
        }
        return dBConnection;
    }
}
