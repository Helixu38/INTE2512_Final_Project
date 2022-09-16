package com.example.intel2512_finalproject;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RentSystem {

    private ArrayList<Account> accountList;
    private ArrayList<Item> itemList;
    private final FileAccRW fileAcc;
    private final FileItemRW fileItem;
    private int currentID;
    private int currentItemID;
    private Account accountFound;

    public RentSystem() {
        
        accountList = new ArrayList<>();
        itemList = new ArrayList<>();
//        itemList.add(new Item("I001-2001", "Medal of Honour", "Game", "1-week",3,3.99,"\t", false));
//        itemList.add(new Item("I002-1988", "White Castle", "Record", "1-week",3,0.99,"Comedy",false));
        fileAcc = new FileAccRW("customer.txt");
        fileItem = new FileItemRW("item.txt");
        // "I003-1992,Alpha Dog,Record,1-week,3,1.99,Action",
        // "I004-1999,Rat Race,DVD,1-week,3,1.99,Horror",
        // "I005-2015,Halo,Game,2-day,2,4.99",
        // "I006-2013,Halloween,DVD,2-day,1,0.99,Horror"));

        this.currentID = 0;
        this.currentItemID = 0;

    }

    /***
     * Add a new user to the system
     * @param name
     * @param address
     * @param phoneNumber
     * @param username
     * @param password
     */
    void addUser(String name, String address, String phoneNumber, String username, String password) {

         // get the account list from the text file
         fileAcc.readAccList();
         accountList = fileAcc.getAccountList();

         // check if user already exists in the local db
         for (Account acc: accountList) {
             if (acc.getUsername().equals(username)) {
                 System.out.println("User with name " + username + " already exists");
                 return;
             }
         }

        // Assuming all users start off as a guest account
        String newID = "C" +  String.format("%03d" , currentID);
        currentID++;
        Guest newUser = new Guest(newID, name, address, phoneNumber, new ArrayList<>(), username, password);

        // add user info to local db and then to text file
        this.accountList.add(newUser);
        fileAcc.addNewAcc(this.accountList);
    }

    /***
     * Add a new title to the system
     * @param title
     * @param loanType
     * @param rentalType
     * @param numberCopies
     * @param rentalFee
     * @param genre
     * @param rentalStatus
     */
    void addItem(String title , String loanType , String rentalType , String numberCopies , String rentalFee ,
                 String genre, String rentalStatus ) {

        // get the item list from the text file
        fileItem.readItemList();
        itemList = fileItem.getItemList();

        // find the largest id in the itemList and continue our id from that number
        for (Item itx : itemList) {
            // if we have id: I012-2022 for example, we take "012" and find the int value of that substring
            int largestID;
            largestID = Integer.parseInt(itx.getId().substring(1, 4));
            if (largestID > currentItemID) {
                currentItemID = largestID;
            }

            // if title already exists, increment the numberCopies count
            if (title.equals(itx.getTitle())) {
                itx.returned();
                fileItem.addItem(this.itemList);
                return;
            }
        }
        currentItemID++;

        // generate new id for the new record
        String newID = "I" +  String.format("%03d" , currentItemID) + "-2022";
        currentItemID++;

        // if new item gets added
        Item newItem = new Item(newID, title, rentalType, loanType, Integer.parseInt(numberCopies), Double.parseDouble(rentalFee),
                genre, Boolean.parseBoolean(rentalStatus));

        // add item info to local db and then to text file
        this.itemList.add(newItem);
        fileItem.addItem(this.itemList);
    }

    /***
     * Login with information supplied in the UI
     * @param username
     * @param password
     * @return
     */
    Account loginUser(String username, String password) {
         fileAcc.readAccList();
         accountList = fileAcc.getAccountList();
         for (Account acc: accountList) {
             if (acc.getUsername().equals(username)) {
                 System.out.println("Found user with name " + username);
                 accountFound = acc;
                 break;
             }
         }

         if (accountFound == null) {
             // account with the given username is not found
             System.out.println("Cannot find user with name " + username);
         }
         else {
             // the account with the matching username is found in the database
             if (!password.equals(accountFound.getPassword())) {
                 System.out.println("password for user " + username + " is incorrect!");
                 return null;
             }
         }
        return accountFound;
    }

    /***
     * Rent an item from entries listed from search/lookup
     * @param itemFound
     */
    void rent(Item itemFound) {

        for (Item itx : itemList) {
            if (itx.getTitle().equals(itemFound.getTitle())) {
                if (itx.getNumberCopies() > 0) {


                    // asserting that we are logged in and the current acc exists
                    assert accountFound != null;

                    // Check if item can be borrowed by account, update account rentalList
                    if (accountFound.borrowMovie(itx)) {
                        // update local item db
                        itx.borrowed();
                        break;
                    } else {
                        System.out.println("Renting unsuccessful due to guest account restrictions");
                        return;
                    }
                }
            }
        }

        // add item info to text file
        fileItem.addItem(this.itemList);

        // update account database as well
        fileAcc.addNewAcc(this.accountList);

    }

    /***
     * Return an item from an entry listed in the search table
     * @param returnedObject
     */
    void returnItem(Item returnedObject) {

        // return item first
        for (Item itx : itemList) {
            if (itx.getTitle().equals(returnedObject.getTitle())) {

                // update local item db
                itx.returned();
                // asserting that we are logged in and the current acc exists
                assert accountFound != null;

                // updating local account db
                accountFound.returnMovie(itx);
                break;

            }
        }
        // after item is returned and user's item returned count ++, check if eligible for promo
        checkPromote(accountFound);

        // add item info to text file
        fileItem.addItem(this.itemList);

        // update account database as well
        fileAcc.addNewAcc(this.accountList);
    }

    /***
     * Check if user is eligible for an account promotion
     * @param user
     */
    void checkPromote(Account user) {

        // promo guest to regular
        if (user.getCustomerType().equals("Guest") && user.getNumReturned() > 3) {
            Regular newRegularAcc = new Regular(
                    user.getId(),
                    user.getName(),
                    user.getAddress(),
                    user.getPhoneNumber(),
                    user.getRentalList(),
                    user.getUsername(),
                    user.getPassword());
            accountList.remove(user);
            accountList.add(newRegularAcc);
        }

        // promo guest to VIP
        else if (user.getCustomerType().equals("Regular") && user.getNumReturned() > 5) {
            VIP newVIPAcc = new VIP(
                    user.getId(),
                    user.getName(),
                    user.getAddress(),
                    user.getPhoneNumber(),
                    user.getRentalList(),
                    user.getUsername(),
                    user.getPassword());
            accountList.remove(user);
            accountList.add(newVIPAcc);
        }
    }

    

    void displayItem(){
        itemList.sort(Comparator.comparing(Item::getId));
        for (Item itx: itemList) {
            // ArrayList<String>sorteditx = itx.stream()sorted(Comparator.naturalOrder());
            System.out.println(itx.toString());
        }
    }

    void displayCustomer(){
        accountList.sort(Comparator.comparing(Account::getId));
        for (Account itx: accountList) {
            // ArrayList<String>sorteditx = itx.stream()sorted(Comparator.naturalOrder());
            System.out.println(itx.toString());
        }
    }  

    void displayOutofstock(){
        for (Item itx: itemList) {
            if(itx.getNumberCopies() == 0) {
                System.out.println(itx);
            }

        }
    }

    public Item search(String id , String title , String loanType , String rentalType , String numberCopies , String rentalFee , String rentalStatus) {

        // get the list of items from the database
        this.fileItem.readItemList();
        this.itemList = this.fileItem.getItemList();

        for (Item itx : itemList) {
            // loop through the list of all items and see if there exists item that matches all search patterns
            if (
                    regexHelper(id, itx.getId()) &&
                    regexHelper(title, itx.getTitle()) &&
                    regexHelper(loanType, itx.getLoanType()) &&
                    regexHelper(rentalType, itx.getRentalType()) &&
                    regexHelper(numberCopies, String.valueOf(itx.getNumberCopies())) &&
                    regexHelper(rentalFee, String.valueOf(itx.getRentalFee())) &&
                    regexHelper(rentalStatus, String.valueOf(itx.getRentalStatus()))) {
                return itx;
            }
        }

        return null;
    }

    /***
     * A helper function for the search method that checks entry against the regex pattern
     * @param information
     * @param result
     * @return whether the result fits the pattern described by the information inputted
     */
    public static boolean regexHelper(String information, String result) {
        if (result.equals("")) {
            return true;
        }
        Pattern pattern = Pattern.compile(information, Pattern.CASE_INSENSITIVE);
        Matcher matcherID = pattern.matcher(result);
        return matcherID.find();
    }
}
