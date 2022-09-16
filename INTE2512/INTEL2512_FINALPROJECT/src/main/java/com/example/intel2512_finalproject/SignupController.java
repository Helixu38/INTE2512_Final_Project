package com.example.intel2512_finalproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    @FXML
    private Button button_login;

    @FXML
    private Button button_signup;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_address;

    @FXML
    private TextField tf_phoneNumber;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_login.setOnAction(event -> SystemUtils.changeScene(event, "Login.fxml" , "Login" , null , null ,null , null ,null ));

        button_signup.setOnAction(event -> {
            if(!tf_name.getText().trim().isEmpty() && !tf_address.getText().trim().isEmpty() && !tf_phoneNumber.getText().trim().isEmpty() && !tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()){
                SystemUtils.signUpUser(event, tf_name.getText() , tf_address.getText() , tf_phoneNumber.getText() , tf_username.getText() , tf_password.getText());
                SystemUtils.changeScene(event, "afterLogin.fxml", "Welcome !" , tf_username.getText() , tf_name.getText(), tf_address.getText(), tf_phoneNumber.getText(), "Guest");

            }   else {
                System.out.print("Please fill in all information");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill in all information to signup");
                alert.show();
            }
        });

    }
}
