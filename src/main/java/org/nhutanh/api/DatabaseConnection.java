/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.nhutanh.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author trann
 */
public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://0.0.0.0:3000/student_sys";
    private static final String USER = "root";
    private static final String PASSWORD = "my-secret-pw";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
