package snakeGame;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {
    static final String driverClassName;
    static final String url;
    static final String username;
    static final String password;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        driverClassName = properties.getProperty("driverClassName");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    public static Connection getConnection() {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

//    public static void release(Statement statement, Connection connection) {
//        if(statement != null) {
//            try {
//                statement.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        statement = null;
//        if(connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        connection = null;
//    }
//
//    public static void release(ResultSet rs, Statement statement, Connection connection) {
//        if(rs != null) {
//            try {
//                rs.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        rs = null;
//        release(statement, connection);
//    }
}
