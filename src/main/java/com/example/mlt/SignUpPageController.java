package com.example.mlt;
//import all the libraries needed
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
    //Switch to Login Page
    @FXML
    void BackLoginButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"Login Page.fxml","MEOW RAPID TRANSIT");
    }
    //A method to sign up a new account
    @FXML
    void SignUpButton(ActionEvent event) throws IOException, SQLException {
            //Get connection to mysql database to store the new user information
            Connection connection = database.getConnections();
            String userName = UsernameEntered.getText();
            String userPassword = PasswordEntered.getText();
            String userEmail = EmailEntered.getText();
            //The filed cannot be empty
            if (userName.isEmpty()  || userPassword.isEmpty() || userEmail.isEmpty()) {
                CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
            //The email format must be correct
            } else if (!EmailEntered.getText().matches("^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@+[A-Za-z0-9-]+([.][A-Za-z0-9]+)")) {
                CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Make sure your email format is correct");
            //Password and confirm password must be same
            }else if (!PasswordEntered.getText().equals(ConfirmPasswordEntered.getText())){
                CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Password and Confirm Password does not match");
             }else {
                //This process insert the user information into mysql database
                String sql = "INSERT INTO user(username, email, password, balance, contactnum) VALUES(?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                //Set data into username,email,password,balance and contact number
                preparedStatement.setString(1, userName);
                preparedStatement.setString(2, userEmail);
                preparedStatement.setString(3, userPassword);
                preparedStatement.setString(4, String.valueOf(0.0));
                preparedStatement.setString(5, String.valueOf(0));

                try{
                    //if the inserting process is successful, pop the success information and switch to the home page
                    if(preparedStatement.executeUpdate()==1) {
                        CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "Sign-up Successful!");
                        new CommonTask().switchScene(event, "Login Page.fxml", "MEOW RAPID TRANSIT");
                    //if the user information is being used by others, it pops out the error message
                    }else{
                        CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "Account or Username already exists with this Email!");
                    }
                } catch (SQLException e){
                    CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "Account or Username already exists with this Email!");
                }
            }

        }
    }



