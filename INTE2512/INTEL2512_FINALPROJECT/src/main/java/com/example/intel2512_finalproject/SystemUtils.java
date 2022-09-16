package com.example.intel2512_finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SystemUtils {
    static RentSystem rentSystem = new RentSystem();

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String name, String address, String phoneNumber, String accountType) {
        Parent root = null;

        if (username != null && address != null && phoneNumber != null && accountType != null) {
            try {
                FXMLLoader loader = new FXMLLoader(SystemUtils.class.getResource(fxmlFile));
                root = loader.load();
                AfterLoginController afterLoginController = loader.getController();
                afterLoginController.setUserInformation(name, address, phoneNumber, accountType, username);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(SystemUtils.class.getResource(fxmlFile));

            } catch (IOException e) {
                e.printStackTrace();

            }

        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle((title));
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void signUpUser(ActionEvent event, String name, String address, String phoneNumber, String username, String password) {

        rentSystem.addUser(name, address, phoneNumber, username, password);
    }

    public static Account loginUser(ActionEvent event, String username, String password) {

        Account accountLogin = rentSystem.loginUser(username, password);
        if (accountLogin == null) {
            // either password is wrong or acc not exist
            System.out.println("Incorrect username and password !");
        }
        // the information for the account is in object [accountLogin]
//            System.out.println("Welcome " + accountLogin.getUsername() + " ! ");
//            System.out.println("Name : " + accountLogin.getName());
//            System.out.println("Address : " + accountLogin.getName());
//            System.out.println("Phone Number : " + accountLogin.getName());

        return accountLogin;

    }

    public static Item lookUp(ActionEvent event, String id, String title, String loanType, String rentalType, String numberCopies, String rentalFee, String rentalStatus) {

        // if this returns null it means there is no matching result
        return rentSystem.search(id, title, loanType, rentalType, numberCopies, rentalFee, rentalStatus);

    }

    public static void addItem(ActionEvent event, String title, String loanType, String rentalType, String numberCopies, String rentalFee,
                               String genre, String rentalStatus) {

        rentSystem.addItem(title, loanType, rentalType, numberCopies, rentalFee, genre, rentalStatus);
    }

    public static void rentItem(ActionEvent event, Item itemRented) {

        // we only rent item that shows up in our search result, displayed in the table, so it's guaranteed it exists
        rentSystem.rent(itemRented);
    }

    public static void returnItem(ActionEvent event, Item itemReturned) {

        // we only return item that shows up in the table
        rentSystem.returnItem(itemReturned);
    }
}
