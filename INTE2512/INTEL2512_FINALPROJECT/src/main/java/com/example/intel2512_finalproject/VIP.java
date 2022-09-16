package com.example.intel2512_finalproject;

import java.util.ArrayList;

public class VIP extends Account{

    int rewardPoints;


    public VIP(String id, String name, String address, String phoneNumber, ArrayList<String> rentalList, String username, String password) {
        super(id, name, address, phoneNumber, rentalList, username, password);
        super.setCustomerType("VIP");
        this.rewardPoints = 0;
    }

    @Override
    public boolean borrowMovie(Item a) {
        // rent 1 item for free if > 100 reward points
        if (this.getRewardPoints() >= 100) {
            this.rewardPoints -= 100;
            a.setRentalFee(0.0);
        }
        super.borrowItem(a);

        // get 10 points for each rental
        this.rewardPoints += 10;
        return true;
    }

    @Override
    public boolean returnMovie(Item a) {
        super.returnMovie(a);
        return true;
    }

    public int getRewardPoints() {
        return this.rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }
}
