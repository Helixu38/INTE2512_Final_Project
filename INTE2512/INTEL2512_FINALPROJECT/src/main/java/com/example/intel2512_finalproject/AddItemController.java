package com.example.intel2512_finalproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddItemController implements Initializable {
    @FXML
    private Button button_add;

    @FXML
    private Button button_back;

    @FXML
    private TextField tf_title;

    @FXML
    private TextField tf_loanType;

    @FXML
    private TextField tf_rentalType;

    @FXML
    private TextField tf_rentalFee;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               SystemUtils.changeScene(event , "rentingInterface.fxml" , "Renting " , null , null , null ,null , null);
            }
        });

        button_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("currently adding title " + tf_title.getText());
                SystemUtils.addItem(event, tf_title.getText() , tf_loanType.getText() , tf_rentalType.getText() , String.valueOf(1), tf_rentalFee.getText() , "horror", "False");
            }
        });

    }
}
