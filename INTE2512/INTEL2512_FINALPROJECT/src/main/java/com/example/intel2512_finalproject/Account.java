package com.example.intel2512_finalproject;

import java.util.ArrayList;

public class Account implements java.io.Serializable{
    private String id;
    private String name;
    private String address;
    private String phoneNumber;
    private ArrayList<String> rentalList;
    private String Username;
    private String Password;

    private String customerType;

    private int numReturned;

    public Account(String id, String name, String address, String phoneNumber, ArrayList<String> rentalList, String username, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.rentalList = rentalList;
        Username = username;
        Password = password;
    }



    public void borrowItem(Item a){
        this.rentalList.add(a.getTitle());
    }

    public boolean returnMovie(Item a){
        if (!this.rentalList.remove(a.getTitle())) {
            System.out.println("movie " + a.getTitle() + "not borrowed by user " + getUsername());
            return false;
        }
        this.numReturned++;
        return true;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<String> getRentalList() {
        return rentalList;
    }

    public void setRentalList(ArrayList<String> rentalList) {
        this.rentalList = rentalList;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public int getNumReturned() {
        return this.numReturned;
    }

    public void setNumReturned(int numReturned) {
        this.numReturned = numReturned;
    }

    @Override
    public String toString() {
        return "id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", rentalList='" + getRentalList() + "'" +
            ", Username='" + getUsername() + "'" +
            ", Password='" + getPassword() + "'" +
            ", customerType='" + getCustomerType() + "'" +
            ", numReturned='" + getNumReturned() + "'";
    }

    public boolean borrowMovie(Item itx) {
        return false;
    }
}
