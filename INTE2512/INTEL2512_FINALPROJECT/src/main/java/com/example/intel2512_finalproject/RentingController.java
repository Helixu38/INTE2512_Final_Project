package com.example.intel2512_finalproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class RentingController implements Initializable {
    private Label label;
    private ObservableList<Item> newList = FXCollections.observableArrayList();
    //Textfield
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfrentalType;
    @FXML
    private TextField tfloanType;
    @FXML
    private TextField tfnumberCopies;
    @FXML
    private TextField tfrentalFee;
    @FXML
    private TextField tfrentalStatus;

    //Table
    @FXML
    private TableView<Item> tvMovies;
    @FXML
    private TableColumn<Item , String> colid;
    @FXML
    private TableColumn<Item , String> colTitle;
    @FXML
    private TableColumn<Item , String> colrentalType;
    @FXML
    private TableColumn<Item , String> colloanType;
    @FXML
    private TableColumn<Item , Integer> colnumberCopies;
    @FXML
    private TableColumn<Item , Double> colrentalFee;
    @FXML
    private TableColumn<Item , String> colrentalStatus;

    @FXML
    private Button button_add;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnRent;

    @FXML
    private Button button_search;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colrentalType.setCellValueFactory(new PropertyValueFactory<>("rentalType"));
        colloanType.setCellValueFactory(new PropertyValueFactory<>("loanType"));
        colnumberCopies.setCellValueFactory(new PropertyValueFactory<>("numberCopies"));
        colrentalFee.setCellValueFactory(new PropertyValueFactory<>("rentalFee"));
//        colrentalStatus.setCellValueFactory(new PropertyValueFactory<Item, String>("rentalStatus"));

        button_search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newList = FXCollections.observableArrayList();
                Item foundItem = SystemUtils.lookUp(event , tfId.getText() , tfTitle.getText() , tfloanType.getText() ,
                        tfrentalType.getText() , tfnumberCopies.getText() , tfrentalFee.getText() , tfrentalStatus.getText());
                if (foundItem == null) {
                    System.out.println("Item " + tfTitle.getText() + " is not found");
                } else {
                    System.out.println(foundItem);
                    newList.add(foundItem);
                    tvMovies.setItems(newList);
                }
            }


        });

        button_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SystemUtils.changeScene(event , "addItem.fxml" , "Add Item ! " , null , null , null,null ,null);
            }
        });

        btnRent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SystemUtils.rentItem(actionEvent, newList.get(0));
            }
        });
        btnLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SystemUtils.changeScene(actionEvent, "Login.fxml" , "Login !" , null , null , null , null , null);
            }
        });
    }
}
