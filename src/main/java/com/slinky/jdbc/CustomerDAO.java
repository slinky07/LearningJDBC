package com.slinky.jdbc;

import com.slinky.jdbc.pojo.Customer;
import com.slinky.jdbc.util.DataAccessObject;
import com.slinky.jdbc.util.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends DataAccessObject<Customer> {
    private static final String INSERT =
            "INSERT INTO CUSTOMER ( first_name, last_name," +
            " email, phone, address, city, state, zipcode)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_ONE =
            "SELECT customer_id, first_name, last_name," +
            "email, phone, address, city, state, zipcode " +
            "FROM customer WHERE customer_id =? ";

    private static final String UPDATE =
            "UPDATE customer SET first_name = ?, last_name=?, " +
            "email = ?, phone = ?, address = ?, city = ?, state = ?," +
            " zipcode = ? WHERE customer_id = ?";

    private static final String DELETE =
            "DELETE FROM customer WHERE customer_id = ?";

    private static final String GET_ALL_LIMIT = "SELECT customer_id, first_name, last_name," +
            "email, phone, address, city, state, zipcode " +
            "FROM customer ORDER BY last_name, first_name LIMIT ? ";
 private static final String GET_ALL_PAGED = "SELECT customer_id, first_name, last_name," +
            "email, phone, address, city, state, zipcode " +
            "FROM customer ORDER BY last_name, first_name LIMIT ? OFFSET ?";

    private static final String ID_NAME = "customer_id";

    public CustomerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Customer findById(long id) {
        Customer customer = new Customer();
        Utils.setFromFind(connection, customer, ID_NAME, GET_ONE, id);
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer update(Customer dto) {
        Customer customer = null;
        try {
            this.connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE);) {
            opsCustomerParams(dto, statement);
            statement.setLong(9, dto.getId()); // which ID to update.
            statement.execute();
            this.connection.commit();
            customer = this.findById(dto.getId());

        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
                throw new RuntimeException(exception);
            }
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return customer;
    }

    @Override
    public Customer create(Customer dto) {
        try (PreparedStatement statement = this.connection.prepareStatement(INSERT);) {
            opsCustomerParams(dto, statement);
            statement.execute();

            int id = this.getLastVal(CUSTOMER_SEQUENCE);
            return this.findById(id);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement statement = this.connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Customer> findAllSorted(int limit) {
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_LIMIT)) {
            statement.setInt(1, limit);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                Utils.handlePerson(customer, ID_NAME, rs); // could have also searched the ID maybe but slower since it searches again
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return customers;
    }
    public List<Customer> findAllPaged(int limit, int pageNumber) {
        List<Customer> customers = new ArrayList<>();
        int offset = ((pageNumber)* limit);
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_PAGED)) {
            if (limit < 1) {
                limit = 10;
            }
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                Utils.handlePerson(customer, ID_NAME, rs); // could have also searched the ID maybe but slower since it searches again
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return customers;
    }

    /**
     * set Statement strings from a Customer object.
     * Only Customer's tables columns are included here
     * @param dto is DataTransfterObject of customer.
     * @param statement is Statement
     * @throws SQLException to handle.
     */
    private void opsCustomerParams(Customer dto, PreparedStatement statement) throws SQLException {
        statement.setString(1, dto.getFirstName());
        statement.setString(2, dto.getLastName());
        statement.setString(3, dto.getEmail());
        statement.setString(4, dto.getPhone());
        statement.setString(5, dto.getAddress());
        statement.setString(6, dto.getCity());
        statement.setString(7, dto.getState());
        statement.setString(8, dto.getZipCode());
    }

}
