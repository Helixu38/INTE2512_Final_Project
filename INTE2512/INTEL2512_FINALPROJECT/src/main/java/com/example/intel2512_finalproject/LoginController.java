package com.example.intel2512_finalproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public LoginController(){

    }


    @FXML
    private Button button_signup;

    @FXML
    private Button button_signin;

    @FXML
    private Label wrongLogin;

    @FXML private TextField tf_username;

    @FXML
    private PasswordField pf_password;

//    public void userLogin(ActionEvent event) throws IOException{
//
//            if(tf_username.getText().toString().equals("minhdinh") && pf_password.getText().toString().equals("123456")) {
//                wrongLogin.setText("Success");
//
//
//            }
//
//            else if(tf_username.getText().isEmpty() && pf_password.getText().isEmpty()){
//                wrongLogin.setText("Please enter your username and password");
//            }
//
//            else{
//                wrongLogin.setText("Wrong username or password");
//            }
//
//
//    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_signin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Account signedInAcc = SystemUtils.loginUser(event , tf_username.getText() , pf_password.getText());
                if (signedInAcc == null) {
                    wrongLogin.setText("Wrong username or password");
                } else {
                    SystemUtils.changeScene(event, "afterLogin.fxml", "Welcome " , signedInAcc.getUsername() , signedInAcc.getName() , signedInAcc.getAddress() , signedInAcc.getPhoneNumber() , "Guest");
                }

            }
        });
        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SystemUtils.changeScene(event, "Signup.fxml", "Signup" , null , null , null ,null , null);
            }
        });

    }
}
