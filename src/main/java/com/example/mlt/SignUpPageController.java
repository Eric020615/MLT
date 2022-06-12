package com.example.mlt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class SignUpPageController {

    @FXML
    private PasswordField ConfirmPasswordEntered;

    @FXML
    private TextField EmailEntered;

    @FXML
    private PasswordField PasswordEntered;

    @FXML
    private TextField UsernameEntered;

    @FXML
    void BackLoginButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"Login Page.fxml","MEOW RAPID TRANSIT");
    }

    @FXML
    void SignUpButton(ActionEvent event) throws IOException, SQLException {
            Connection connection = database.getConnections();
            String userName = UsernameEntered.getText();
            String userPassword = PasswordEntered.getText();
            String userEmail = EmailEntered.getText();
            if (userName.isEmpty()  || userPassword.isEmpty() || userEmail.isEmpty()) {
                CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
            } else if (!EmailEntered.getText().matches("^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@+[A-Za-z0-9-]+([.][A-Za-z0-9]+)")) {
                CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Make sure your email format is correct");
            }else if (!PasswordEntered.getText().equals(ConfirmPasswordEntered.getText())){
                CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Password and Confirm Password does not match");
             }else {

                String sql = "INSERT INTO user(username, email, password, balance, contactnum) VALUES(?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, userName);
                preparedStatement.setString(2, userEmail);
                preparedStatement.setString(3, userPassword);
                preparedStatement.setString(4, String.valueOf(0.0));
                preparedStatement.setString(5, String.valueOf(0));

                try{

                    if(preparedStatement.executeUpdate()==1) {
                        CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "Sign-up Successful!");
                        new CommonTask().switchScene(event, "Login Page.fxml", "MEOW RAPID TRANSIT");
                    }else{
                        CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "Account or Username already exists with this Email!");
                    }
                } catch (SQLException e){
                    CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "Account or Username already exists with this Email!");
                }
            }

        }
    }



