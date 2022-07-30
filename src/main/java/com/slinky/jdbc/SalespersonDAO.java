package com.slinky.jdbc;

import com.slinky.jdbc.pojo.Salesperson;
import com.slinky.jdbc.util.DataAccessObject;

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
    public SalespersonDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Salesperson findById(long id) {
        Salesperson salesperson = new Salesperson();

        try (PreparedStatement statement = this.connection.prepareStatement(GET_BY_ID)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                salesperson.setId(rs.getLong("salesperson_id"));
                salesperson.setFirstName(rs.getString("first_name"));
                salesperson.setLastName(rs.getString("last_name"));
                salesperson.setEmail(rs.getString("email"));
                salesperson.setPhone(rs.getString("phone"));
                salesperson.setAddress(rs.getString("address"));
                salesperson.setCity(rs.getString("city"));
                salesperson.setState(rs.getString("state"));
                salesperson.setZipCode(rs.getString("zipcode"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
