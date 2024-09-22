/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Khanh
 */
public class DBUtils {
    public static Connection initializeDatabase() throws Exception {
        String dbDriver = "com.mysql.cj.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:1433/";
        String dbName = "appointment_db";
        String dbUsername = "sa";
        String dbPassword = "12345";

        Class.forName(dbDriver);
        Connection conn = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);
        return conn;
    }

}
