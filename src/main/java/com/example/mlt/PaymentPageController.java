package com.example.mlt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;


public class PaymentPageController {

    @FXML
    private Label accountBalanceLabel;

    @FXML
    private Button HomeIcon;

    @FXML
    private Button ProfileIcon;

    @FXML
    void topUpButtonPressed(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Top Up Page.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Top up");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void initialize() {
        //accountBalanceLabel.setText(String.format("%.2f", User.getBalance()));
    }

    @FXML
    void HomeButton(ActionEvent event) throws IOException{
        new CommonTask().switchScene(event,"Home Page.fxml","MEOW RAPID TRANSIT");
    }

    @FXML
    void ProfileButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"User Profile.fxml","MEOW RAPID TRANSIT");
    }

    @FXML
    void NavigationButton(ActionEvent event) throws IOException{
        new CommonTask().switchScene(event,"Navigation Page.fxml","MEOW RAPID TRANSIT");
    }

    @FXML
    void HistogramButton(ActionEvent event) throws IOException{
    }
}