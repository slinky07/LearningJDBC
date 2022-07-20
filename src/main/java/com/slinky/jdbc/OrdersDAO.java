package com.slinky.jdbc;

import com.slinky.jdbc.pojo.Orders;
import com.slinky.jdbc.util.DataAccessObject;

import java.sql.Connection;
import java.util.List;

public class OrdersDAO extends DataAccessObject<Orders> {
    private static final String GET_ONE = ""; //TODO: implement from challenge. check screenshot.
    public OrdersDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Orders findById(long id) {
        //TODO: implement so that this create one or more Order Items as needed.
        return null;
    }

    @Override
    public List<Orders> findAll() {
        return null;
    }

    @Override
    public Orders update(Orders dto) {
        return null;
    }

    @Override
    public Orders create(Orders dto) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
