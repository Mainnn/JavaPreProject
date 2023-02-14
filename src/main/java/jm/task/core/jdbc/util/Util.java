package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public static final   Connection conn = getConnectionDB();

    private static final  String userName = "root";
    private static final  String password = "root";
    private static final String url = "jdbc:mysql://localhost:3306/users";

    private static Connection getConnectionDB()  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
