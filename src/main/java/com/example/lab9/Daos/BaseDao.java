package com.example.lab9.Daos;
import java.sql.*;
public class BaseDao {

    public Connection getConnection() throws SQLException {
        String user = "root";
        String pass = "iweb";
        String url = "jdbc:mysql://localhost:3306/mydb";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(url, user, pass);
    }

}

