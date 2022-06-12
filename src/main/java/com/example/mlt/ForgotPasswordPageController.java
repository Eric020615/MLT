package com.example.mlt;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ForgotPasswordPageController {

    @FXML
    private TextField ConfirmPasswordEntered;

    @FXML
    private TextField EmailEntered;

    @FXML
    private TextField PasswordEntered;

    public void ChangePassword(Event event) {
        Connection connection = database.getConnections();
        String email = EmailEntered.getText();
        String newpassword = PasswordEntered.getText();
        String againpassword = ConfirmPasswordEntered.getText();
        if (!email.matches("^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@+[A-Za-z0-9-]+([.][A-Za-z0-9]+)")) {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Make sure your email format is correct");
        } else if (!againpassword.equals(newpassword)) {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Two passwords are not match !");
        } else {
            try {
                String sql = "UPDATE `fopgui`.`user`" + "SET" + "`password` = ? " + "WHERE `email` = ?;";

                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, againpassword);
                statement.setString(2, email);
                if(statement.executeUpdate()==1) {
                    CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "Your Password Is Changed Successful");
                }else{
                    CommonTask.showAlert(Alert.AlertType.ERROR, "Failed", "This email does not exist.");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                database.closeConnections();
            }
        }
        Node n = (Node) event.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

}
