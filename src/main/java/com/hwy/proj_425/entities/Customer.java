package com.hwy.proj_425.entities;

import javax.validation.constraints.Digits;

public class Customer {
    private Integer id;
    private String firstName;
    private String lastName;
//    @Digits(integer = 6,fraction = 2,message = "Please input digits!")
    private Integer totPoint;
    private Integer avaPoint;
    private String address;
    private String fakeTot;

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


    public String getFakeTot() {
        return fakeTot;
    }

    public void setFakeTot(String fakeTot) {
        this.fakeTot = fakeTot;
    }

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
