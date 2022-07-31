package com.slinky.jdbc;

import com.slinky.jdbc.pojo.Customer;
import com.slinky.jdbc.pojo.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
            "localhost", "hplussport",
            "postgres", "password"
        );
        try {
            Connection connection = dcm.getConnection();
            CustomerDAO customerDAO = new CustomerDAO(connection);
            OrdersDAO ordersDAO = new OrdersDAO(connection);
//            routine2(customerDAO);
//            routine(customerDAO);
//            getOrder(ordersDAO, 1000);
//            getOrderSP(ordersDAO,789);
//            getAllSorted(customerDAO, 20);
            getAllPaged(customerDAO, 10);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void routine(CustomerDAO customerDAO) {
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

//        customerDAO.delete(dbCustomer.getId()); // how to delete a record.

    }
    private static void routine2(CustomerDAO customerDAO) {
        Customer customer = new Customer();
        customer.setFirstName("George");
        customer.setLastName("Washington");
        customer.setEmail("gwashington.wh.gov");
        customer.setAddress("1234 Main St");
        customer.setCity("Washington");
        customer.setState("DC");
        customer.setPhone("(555) 555-4131");
        customer.setZipCode("02234");

        Customer dbCustomer = customerDAO.create(customer);
        System.out.println(dbCustomer);

        dbCustomer = customerDAO.findById(dbCustomer.getId());
        System.out.println(dbCustomer);

        dbCustomer.setEmail("george.washington@wh.gov");
        dbCustomer = customerDAO.update(dbCustomer);
        System.out.println(dbCustomer);

    }

    private static void getOrder(OrdersDAO ordersDAO, long l) {
        Order dbOrder = ordersDAO.findById(l);
        System.out.println(dbOrder);
    }
    private static void getOrderSP (OrdersDAO ordersDAO, long l) {
        List<Order> orders = ordersDAO.getOrdersForCustomer(l);
        orders.forEach(System.out::println);
    }

    private static void getAllSorted(CustomerDAO customerDAO, int limit) {
        customerDAO.findAllSorted(limit).forEach(System.out::println);
    }
    private static void getAllPaged(CustomerDAO customerDAO, int limit) {
        System.out.println("paged");
        for (int i = 0; i < 3; i++) {
            System.out.println("Page Number " + i);
            customerDAO.findAllPaged(limit, i).forEach(System.out::println);
        }
    }
}
