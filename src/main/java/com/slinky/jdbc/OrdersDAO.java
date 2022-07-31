package com.slinky.jdbc;

import com.slinky.jdbc.pojo.*;
import com.slinky.jdbc.util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAO extends DataAccessObject<Orders> {
    private static final String GET_BY_ID = "SELECT " +
            "o.order_id, o.creation_date, o.total_due,o.status as orders_status, " +
            "o.customer_id, o.salesperson_id,"+
            "s.first_name, s.last_name, s.email, " +
            "oi.quantity, " +
            "p.product_id, p.code, p.name, p.size, p.variety, p.price, p.status as product_status " +
            "FROM orders o " +
            "join customer c on o.customer_id=c.customer_id " +
            "join salesperson s on o.salesperson_id = s.salesperson_id " +
            "join order_item oi on o.order_id = oi.order_id " +
            "join product p on oi.product_id = p.product_id " +
            "WHERE o.order_id=?";
    public OrdersDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Orders findById(long id) {
        Orders orders = new Orders();
        Customer customer;
        Salesperson salesperson = new Salesperson();

        List<OrderItem> orderItemList = new ArrayList<>();
        orders.setOrderItemList(orderItemList);

        try (PreparedStatement statement = this.connection.prepareStatement(GET_BY_ID)) {
        statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            long orderId = 0;

            while (rs.next()) {
                if (orderId == 0) { //prevent these being set multiple time
                    opsOrderSet(rs, orders);
                    orderId = orders.getId();

                    CustomerDAO customerDAO = new CustomerDAO(this.connection);
                    customer = customerDAO.findById(rs.getLong("customer_id"));
                    orders.setCustomer(customer);

                    SalespersonDAO salespersonDAO = new SalespersonDAO(this.connection);
                    salesperson = salespersonDAO.findById(rs.getLong("salesperson_id"));
                    orders.setSalesperson(salesperson);
                }
                // create order_item obj
                OrderItem orderItem = new OrderItem();
                opsOrderItemSet(rs, orderItem);

                //TODO: make productDAO
                Product product = new Product();
                opsProductSet(rs, product);

                orderItem.setProduct(product);
                orders.addToList(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return orders;
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

    private void opsProductSet(ResultSet rs, Product product) throws SQLException {
        product.setCode(rs.getString("code"));
        product.setName(rs.getString("name"));
        product.setSize(rs.getInt("size"));
        product.setVariety(rs.getString("variety"));
        product.setPrice(rs.getDouble("price"));
        product.setStatus(rs.getString("product_status"));
    }

    private void opsOrderItemSet(ResultSet rs, OrderItem orderItem) throws SQLException {
        orderItem.setOrder_id(rs.getLong("order_id"));
        orderItem.setProduct_id(rs.getLong("product_id"));
        orderItem.setQuantity(rs.getInt("quantity"));
    }

    private void opsOrderSet(ResultSet rs, Orders orders) throws SQLException {
        orders.setId(rs.getLong("order_id"));
        orders.setCreation_date(rs.getString("creation_date"));
        orders.setTotal_due(rs.getDouble("total_due"));
        orders.setStatus(rs.getString("orders_status"));
        orders.setCustomer_id(rs.getLong("customer_id"));
        orders.setSalesperson_id(rs.getLong("salesperson_id"));
    }
}
