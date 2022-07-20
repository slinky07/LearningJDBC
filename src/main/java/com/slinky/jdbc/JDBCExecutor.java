package com.slinky.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author francis sauve
 * @version 1.0
 * @apiNote  following the LinkedIn Learning course Learning JDBC.
 * this class acts as main entrypoint to the application
 */
public class JDBCExecutor {

    public static void main(String[] args) {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager(
//                "localhost:5432", "hplussport", // this one not necessary in postgres i guess
                "localhost", "hplussport",
                "postgres", "password"
        );
        try {
            Connection connection = dcm.getConnection();
            CustomerDAO customerDAO = new CustomerDAO(connection);
            Customer customer = customerDAO.findById(169);
            System.out.println(customer.getFirstName() + " " + customer.getLastName());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
