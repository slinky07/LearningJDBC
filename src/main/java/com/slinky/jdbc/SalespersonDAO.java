package com.slinky.jdbc;

import com.slinky.jdbc.pojo.Salesperson;
import com.slinky.jdbc.util.DataAccessObject;
import com.slinky.jdbc.util.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SalespersonDAO extends DataAccessObject<Salesperson> {

    private static final String GET_BY_ID= "SELECT " +
            "salesperson_id, first_name, last_name," +
            "email, phone, address, city, state, zipcode " +
            "FROM salesperson WHERE salesperson_id = ?";

    private static final String ID_NAME = "salesperson_id";

    public SalespersonDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Salesperson findById(long id) {
        Salesperson salesperson = new Salesperson();
        Utils.setFromFind(connection, salesperson, ID_NAME, GET_BY_ID, id);
        return salesperson;
    }

    @Override
    public List<Salesperson> findAll() {
        return null;
    }

    @Override
    public Salesperson update(Salesperson dto) {
        return null;
    }

    @Override
    public Salesperson create(Salesperson dto) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
