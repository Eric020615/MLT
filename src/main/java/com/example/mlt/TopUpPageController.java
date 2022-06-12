package com.example.mlt;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.mlt.LoginPageController.getLoginEmail;
import static com.example.mlt.LoginPageController.setLoginEmail;
import static com.example.mlt.PaymentPageController.userBalance;

public class TopUpPageController {

    @FXML
    private TextField ReloadEntered;

    @FXML
    public void ConfirmButton(ActionEvent event) {
        try {
            if ((!ReloadEntered.getText().isBlank()) && (Double.parseDouble(ReloadEntered.getText()) <= Double.MAX_VALUE && Double.parseDouble(ReloadEntered.getText()) > 0)) {
                Connection connection = database.getConnections();
                Double newReload = Double.valueOf(ReloadEntered.getText())+userBalance;

                String sql = "UPDATE `fopgui`.`user` SET `balance` = ? WHERE `email` = ?;";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, String.valueOf(String.format("%.2f", newReload)));
                preparedStatement.setString(2, getLoginEmail());
                if(preparedStatement.executeUpdate()==1) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Top Up Successful");
                    alert.setHeaderText(null);
                    alert.setContentText("Your account balanced has increased");
                    alert.showAndWait();

                    Node n = (Node) event.getSource();
                    Stage stage = (Stage) n.getScene().getWindow();
                    stage.close();

                }


            } else {
                CommonTask.showAlert(Alert.AlertType.ERROR, "Top Up Unuccessful", "Please enter a valid amount");
            }
        } catch (NumberFormatException e) {
            CommonTask.showAlert(Alert.AlertType.ERROR, "Top Up Unuccessful", "Please enter a valid amount");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}


