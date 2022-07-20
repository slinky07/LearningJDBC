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

            Customer customer = new Customer();
            customer.setFirstName("John");
            customer.setLastName("Adams");
            customer.setEmail("jadams.wh.gov");
            customer.setAddress("1234 Main St");
            customer.setCity("Arlington");
            customer.setState("VA");
            customer.setPhone("(555) 555-4231");
            customer.setZipCode("01234");

            Customer dbCustomer = customerDAO.create(customer);
            System.out.println(dbCustomer);

            dbCustomer = customerDAO.findById(dbCustomer.getId());
            System.out.println(dbCustomer);

            dbCustomer.setEmail("john.adams@wh.gov");
            dbCustomer = customerDAO.update(dbCustomer);
            System.out.println(dbCustomer);

            customerDAO.delete(dbCustomer.getId()); //object still exists after this thought

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
