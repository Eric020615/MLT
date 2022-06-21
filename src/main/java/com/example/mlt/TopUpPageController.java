package com.example.mlt;
//import libraries needed in this controller class
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.mlt.LoginPageController.getLoginEmail;
import static com.example.mlt.PaymentPageController.userBalance;
//TopUP Page Controller
public class TopUpPageController {
    //Create TextField name ReloadEntered
    @FXML
    private TextField ReloadEntered;
    //Create On Action Method name ConfirmButton
    @FXML
    public void ConfirmButton(ActionEvent event) {
        try {
            //When all the text fields are empty show the alert message to user
            if ((!ReloadEntered.getText().isBlank()) && (Double.parseDouble(ReloadEntered.getText()) <= Double.MAX_VALUE && Double.parseDouble(ReloadEntered.getText()) > 0)) {
                //Create a connection to the database
                Connection connection = database.getConnections();
                //Get the amount entered by user and add it into the variable with the current balance
                Double newReload = Double.valueOf(ReloadEntered.getText())+userBalance;
                //Create a String variable name sql
                String sql = "UPDATE `fopgui`.`user` SET `balance` = ? WHERE `email` = ?;";
                //Pass the String variable to the .prepareStatement() function and get the value for the statement
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                //Use the .setString() to set the new balance to the database
                preparedStatement.setString(1, String.valueOf(String.format("%.2f", newReload)));
                //Use the .setString() to get Login Email Address
                preparedStatement.setString(2, getLoginEmail());
                if(preparedStatement.executeUpdate()==1) {
                    //Show the alert message to notify the user that the account was successfully reload
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Top Up Successful");
                    //Set the header message to be null
                    alert.setHeaderText(null);
                    //Set the content of the alert message
                    alert.setContentText("Your account balanced has increased");
                    alert.showAndWait();

                    Node n = (Node) event.getSource();
                    Stage stage = (Stage) n.getScene().getWindow();
                    stage.close();

                }


            } else {
                //Show alert message when the user enter invalid amount
                CommonTask.showAlert(Alert.AlertType.ERROR, "Top Up Unuccessful", "Please enter a valid amount");
            }
        } catch (NumberFormatException e) {
            //Show alert message when the user enter invalid amount
            CommonTask.showAlert(Alert.AlertType.ERROR, "Top Up Unuccessful", "Please enter a valid amount");
        } catch (SQLException e) {
            //catch SQLException and print the error message
            e.printStackTrace();
        }

    }
}


