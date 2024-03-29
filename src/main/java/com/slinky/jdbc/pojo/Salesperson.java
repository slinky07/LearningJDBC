package com.slinky.jdbc.pojo;

import com.slinky.jdbc.util.DataTransferObject;
public class Salesperson implements DataTransferObject, Person {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Salesperson{\n" +
                "\tid=" + id +
                ", \n\tfirstName='" + firstName + '\'' +
                ", \n\tlastName='" + lastName + '\'' +
                ", \n\temail='" + email + '\'' +
                ", \n\tphone='" + phone + '\'' +
                ", \n\taddress='" + address + '\'' +
                ", \n\tcity='" + city + '\'' +
                ", \n\tstate='" + state + '\'' +
                ", \n\tzipCode='" + zipCode + '\'' +
                "\n}";
    }
}
