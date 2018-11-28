package com.hwy.proj_425.entities;

public class Customer {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer totPoint;
    private Integer avaPoint;
    private String address;

    public Customer() {
    }

//    public Customer(String id, String firstName, String lastName, Integer totPoint, Integer avaPoint, String address) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.totPoint = totPoint;
//        this.avaPoint = avaPoint;
//        this.address = address;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getTotPoint() {
        return totPoint;
    }

    public void setTotPoint(Integer totPoint) {
        this.totPoint = totPoint;
    }

    public Integer getAvaPoint() {
        return avaPoint;
    }

    public void setAvaPoint(Integer avaPoint) {
        this.avaPoint = avaPoint;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
