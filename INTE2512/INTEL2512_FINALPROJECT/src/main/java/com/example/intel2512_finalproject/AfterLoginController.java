package com.example.intel2512_finalproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AfterLoginController implements Initializable {
    @FXML
    private Button button_continue;

    @FXML
    private Label label_welcome;

    @FXML
    private Label label_name;

    @FXML
    private Label label_address;

    @FXML
    private Label label_phoneNumber;

    @FXML
    private  Label label_accountType;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_continue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SystemUtils.changeScene(event, "rentingInterface.fxml" , "Renting interface !" , null , null , null , null ,null );

            }
        });

    }

    public void setUserInformation(String name, String address , String phoneNumber , String accountType , String username){
        label_welcome.setText("Welcome " + username + " ! " );
        label_name.setText("Name : " + name);
        label_address.setText("Address : " + address);
        label_phoneNumber.setText("Phone Number : " + phoneNumber);
        label_accountType.setText("Account Type : " + accountType );
    }
}
