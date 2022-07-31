package com.slinky.jdbc.pojo;

import com.slinky.jdbc.util.DataTransferObject;

import java.util.Date;
import java.util.List;

public class Order implements DataTransferObject {
    private long id;
    private Date creation_date;
    private double total_due;
    private String status;
    private long customer_id;
    private long salesperson_id;

    private Customer customer;

    private Salesperson salesperson;
    private List<OrderItem> orderItemList;


    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public double getTotal_due() {
        return total_due;
    }

    public void setTotal_due(double total_due) {
        this.total_due = total_due;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public long getSalesperson_id() {
        return salesperson_id;
    }

    public void setSalesperson_id(long salesperson_id) {
        this.salesperson_id = salesperson_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Salesperson getSalesperson() {
        return salesperson;
    }

    public void setSalesperson(Salesperson salesperson) {
        this.salesperson = salesperson;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public void addToList(OrderItem orderItem) {
        orderItemList.add(orderItem);
    }


   /* @Override
    public String toString() {
        return "Orders{" +
                "creation_date='" + creation_date + '\'' +
                ", total_due=" + total_due +
                ", status='" + status + '\'' +
                ", customer_id=" + customer_id +
                ", salesperson_id=" + salesperson_id +
                '}';
    }*/

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Orders {\n" +
                "\tid=" + id + "\n" +
                "\tcreation_date='" + creation_date + '\'' + ",\n" +
                "\ttotal_due=" + total_due + ",\n" +
                "\tstatus='" + status + '\'' + ",\n" +
                "\tcustomer_id=" + customer_id + ",\n" +
                "\tsalesperson_id=" + salesperson_id + ",\n" +
                " \n CUSTOMER= {\n\t" + customer.toString() +
                ", \n}\t\n\n SALESPERSON={\n\t" + salesperson.toString() +
                ", \n}\t\n\n ORDER_ITEM_LIST={\n");
        for (OrderItem orderItem : orderItemList) {
            str.append("\t\t"+orderItem.toString()+",");
            str.append("\n");
            str.append("\t\t"+orderItem.getProduct().toString()+",");
            str.append("\n");
        }
        str.append("\t}\n");
        str.append("}");
        return str.toString();
    }
}
