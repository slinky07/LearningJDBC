package com.slinky.jdbc.util;

import com.slinky.jdbc.pojo.Customer;
import com.slinky.jdbc.pojo.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Utils {
    /**
     * Tables Salesperson and Customer are actually identical, therefore the find by ID methods
     * for those will be duplicated code. I pulled it out and made them common type.
     * @param connection database connection within the superclass.
     * @param person either Customer or Salesperson obj
     * @param ID_NAME is name of ID record in the respective tables.
     * @param GET_BY_ID is a String sql statement.
     * @param id is ID to find.
     */
    public static void setFromFind(Connection connection, Person person, String ID_NAME, String GET_BY_ID, long id) {
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                handlePerson(person, ID_NAME, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public static void handlePerson(Person person, String ID_NAME, ResultSet rs) throws SQLException {
        person.setId(rs.getLong(ID_NAME));
        person.setFirstName(rs.getString("first_name"));
        person.setLastName(rs.getString("last_name"));
        person.setEmail(rs.getString("email"));
        person.setPhone(rs.getString("phone"));
        person.setAddress(rs.getString("address"));
        person.setCity(rs.getString("city"));
        person.setState(rs.getString("state"));
        person.setZipCode(rs.getString("zipcode"));
    }
}
