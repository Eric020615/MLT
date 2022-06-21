package com.example.mlt;
//import all the libraries needed
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

    //A method to change the old password to new password
    public void ChangePassword(Event event) {
        //Get connection
        Connection connection = database.getConnections();
        //Get email, old password and new password information
        String email = EmailEntered.getText();
        String newpassword = PasswordEntered.getText();
        String againpassword = ConfirmPasswordEntered.getText();
        //Check email format correct or not, if not pop out the error message
        if (!email.matches("^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@+[A-Za-z0-9-]+([.][A-Za-z0-9]+)")) {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Make sure your email format is correct");
        //Check the password and confirm password matched or not
        } else if (!againpassword.equals(newpassword)) {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Two passwords are not match !");
        } else {
            try {
                //Update the old password to new password by updating the password through mysql
                String sql = "UPDATE `fopgui`.`user`" + "SET" + "`password` = ? " + "WHERE `email` = ?;";

                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, againpassword);
                statement.setString(2, email);
                //If successful update, pop out the successful information
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

        //Turn off the window automatically when process is finished
        Node n = (Node) event.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

}
