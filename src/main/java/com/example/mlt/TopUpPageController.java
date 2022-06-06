package com.example.mlt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TopUpPageController {

    @FXML
    private TextField ReloadEntered;

    @FXML
    void ConfirmButton(ActionEvent event) {
        try {
            if ((!ReloadEntered.getText().isBlank()) && (Double.parseDouble(ReloadEntered.getText()) <= Double.MAX_VALUE && Double.parseDouble(ReloadEntered.getText()) > 0)) {
                // if top up value is entered
                // display top up successful pop-up message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Top Up Successful");
                alert.setHeaderText(null);
                alert.setContentText("Your account balanced has increased");
                alert.showAndWait();
                //double currentBalance = User.getBalance() + Double.parseDouble(priceEntered.getText());
                //User.setBalance(currentBalance);
                //balanceLabel.setText(String.format("%.2f", User.getBalance()));
                //resetAccountBalance(User.getBalance());

                // top up page will close automatically when product information has been added
                Node n = (Node) event.getSource();
                Stage stage = (Stage) n.getScene().getWindow();
                stage.close();

            } else {
                // if top up value is not entered
                // display top up unsuccessful pop-up message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Top Up Unsuccessful");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid amount");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            // If top up value entered is invalid
            // display top up unsuccessful pop-up message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Top Up Unsuccessful");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid amount");
            alert.showAndWait();
        }

    }
}


