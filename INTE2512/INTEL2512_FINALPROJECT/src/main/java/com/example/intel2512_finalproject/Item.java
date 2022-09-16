package com.example.intel2512_finalproject;

public class Item implements java.io.Serializable {
    private String id;
    private String title;
    private String rentalType;
    private String loanType;
    private int numberCopies;
    private double rentalFee;
    private String genre;
    private boolean rentalStatus;




    public Item (String id, String title, String rentalType, String loanType, int numberCopies, double rentalFee, String genre , boolean rentalStatus) {
        this.id = id;
        this.title = title;
        this.rentalType = rentalType;
        this.loanType = loanType;
        this.numberCopies = numberCopies;
        this.rentalFee = rentalFee;
        this.genre = genre;
        this.rentalStatus = rentalStatus;
    }

    public void borrowed() {
        this.numberCopies--;
    }

    public void returned() {
        this.numberCopies++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(boolean rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRentalType() {
        return this.rentalType;
    }

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }

    public String getLoanType() {
        return this.loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public int getNumberCopies() {
        return this.numberCopies;
    }

    public void setNumberCopies(int numberCopies) {
        this.numberCopies = numberCopies;
    }

    public double getRentalFee() {
        return this.rentalFee;
    }

    public void setRentalFee(double rentalFee) {
        this.rentalFee = rentalFee;
    }

    public boolean getRentalStatus() {
        return this.rentalStatus;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public  String toString() {
        return
                id + '\t' +
                title + '\t' +
                rentalType + '\t' +
                loanType + '\t' +
                numberCopies + '\t' +
                rentalFee + '\t' +
                genre + '\t' +
                rentalStatus ;
    }
}
