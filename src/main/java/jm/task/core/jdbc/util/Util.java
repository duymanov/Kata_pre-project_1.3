package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class Util  {
    private static final  String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static  String DB_URL = "jdbc:mysql://localhost:3306/mydb1";
    private static final String DB_USERNAME = "denis";
    private static final String DB_PASSWORD = "1234";


    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connection complete");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection not complete");

        }
        return connection;
    }
}
