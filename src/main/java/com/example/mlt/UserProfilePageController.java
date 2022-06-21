package com.example.mlt;
//import all the libraries needed
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static com.example.mlt.LoginPageController.getLoginEmail;
import static com.example.mlt.LoginPageController.setLoginEmail;
//UserProfilePageController
public class UserProfilePageController {
    //Create PasswordField name ConfirmPasswordEntered
    @FXML
    private PasswordField ConfirmPasswordEntered;
    //Create TextField name ContactNumberEntered
    @FXML
    private TextField ContactNumberEntered;
    //Create TextField name EmailEntered
    @FXML
    private TextField EmailEntered;
    //Create PasswordField name NewPasswordEntered
    @FXML
    private PasswordField NewPasswordEntered;
    //Create TextField name UsernameEntered
    @FXML
    private TextField UsernameEntered;
    //Create a OnAction method name BackHomePageButton
    @FXML
    void BackHomePageButton(ActionEvent event) throws IOException {
        //Pass the arguments to the switchScene method to switch to Home Page with title MEOW RAPID TRANSIT
        new CommonTask().switchScene(event,"Home Page.fxml","MEOW RAPID TRANSIT");
    }
    //Create a OnAction method name DeleteButton
    @FXML
    void DeleteButton(ActionEvent event) {
            //Alert the user for getting confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            //Set title "Delete account ?"
            alert.setTitle("Delete account ?");
            //Set header "Do you confirm to delete your account ?"
            alert.setHeaderText("Do you confirm to delete your account ?");
            //Show and wait
            alert.showAndWait();
            //Show alert
            alert.show();
            //When user click OK
            if (alert.getResult() == ButtonType.OK) {
                try {
                    //Create connection to the database
                    Connection connection = database.getConnections();
                    //Create a String variable name sql
                    String sql = "DELETE FROM `fopgui`.`user`\n" + "WHERE `email` =? ";
                    //Pass the String variable to the .prepareStatement() function and get the value for the statement
                    PreparedStatement statement = connection.prepareStatement(sql);
                    //Use the .setString() to get the Email address
                    statement.setString(1, getLoginEmail());
                    if(statement.executeUpdate()==1) {
                        //Show the alert message to notify the user that the account was successfully deleted
                        CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "The account " + getLoginEmail() + " is deleted!");
                        //Pass the arguments to the switchScene method to switch to Home Page with title MEOW RAPID TRANSIT
                        new CommonTask().switchScene(event, "User Profile.fxml", "MEOW RAPID TRANSIT");
                        //Set the email address to be null in the database
                        setLoginEmail(null);
                    }else{
                        //Show the alert message that notify the user that the account was fail to delete
                        CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "Fail deleted");
                    }
                } catch (SQLException e) {
                    //Catch the Exception and print it
                    System.out.println(e);
                } catch (IOException e) {
                    //Catch the IOException and print it
                    e.printStackTrace();
                }finally {
                    //Close the connection
                    database.closeConnections();
                }
            }
            //Close the alert
            alert.close();
        }

    //Create a SaveButton Method that throws SQLException
    @FXML
    void SaveButton(ActionEvent event) throws SQLException {
            //Create a connection to the database
            Connection connection = database.getConnections();
            //Get username
            String newUsername = UsernameEntered.getText();
            //Get email address
            String newEmail = EmailEntered.getText();
            //Get contact number
            String newContactnumber = ContactNumberEntered.getText();
            //Get password
            String newPassword = NewPasswordEntered.getText();
            //Get confirm password
            String newConfirmpassword= ConfirmPasswordEntered.getText();
            //if the fields are empty show the alert message to user
            if ( newUsername.isEmpty() || newEmail.isEmpty() || newContactnumber.isEmpty() || newPassword.isEmpty() || newConfirmpassword.isEmpty()) {
                CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
            }
            //if the password and confirm password are not identical show the alert message to user
            else if (!newPassword.equals(newConfirmpassword)){
                CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Password and Confirm Password does not match");
            }
            //else
            else {
                //Create a String variable name sql
                String sql = "UPDATE `fopgui`.`user` SET `username` = ?,`email` = ?,`password` = ?,`contactnum` = ? WHERE `email` = ?;";
                //Pass the String variable to the .prepareStatement() function and get the value for the statement
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                //Use the .setString() to set the Username
                preparedStatement.setString(1, newUsername);
                //Use the .setString() to set the new Email address
                preparedStatement.setString(2, newEmail);
                //Use the .setString() to set the confirmed password
                preparedStatement.setString(3, newConfirmpassword);
                //Use the .setString() to set the contact number
                preparedStatement.setString(4, newContactnumber);
                //Use the .setString() to set the Email address
                preparedStatement.setString(5, getLoginEmail());

                try {
                    //When the account updated successfully show alert message to the user
                    if(preparedStatement.executeUpdate()==1) {
                        CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "Your user profile is saved successful");
                        setLoginEmail(newEmail);
                    }else{
                        //When the account was not updated successfully show alert message
                        CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "Failed !! Change unsuccessful !");
                    }
                } catch (SQLException e) {
                    //Catch the SQLException and print the error message
                    System.out.println(e);
                }finally {
                    //Close the database connection
                    database.closeConnections();
                }
            }

        }

}


