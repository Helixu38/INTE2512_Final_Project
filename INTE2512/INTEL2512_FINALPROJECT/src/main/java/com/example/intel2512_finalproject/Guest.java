package com.example.intel2512_finalproject;

import java.util.ArrayList;

public class Guest extends Account{
    public Guest(String id, String name, String address, String phoneNumber, ArrayList<String> rentalList, String username, String password) {
        super(id, name, address, phoneNumber, rentalList, username, password);
        super.setCustomerType("Guest");
    }

    @Override
    public boolean borrowMovie(Item a) {
        if (getRentalList().size() >= 2 || a.getLoanType().equals("2-day")) {
            return false;
        }
        super.borrowItem(a);
        return true;
    }

    @Override
    public boolean returnMovie(Item a) {
        super.returnMovie(a);
        return true;
    }
}
