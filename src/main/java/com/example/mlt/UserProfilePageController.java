package com.example.mlt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import static com.example.mlt.LoginPageController.getLoginEmail;
import static com.example.mlt.LoginPageController.setLoginEmail;

public class UserProfilePageController {

    @FXML
    private PasswordField ConfirmPasswordEntered;

    @FXML
    private TextField ContactNumberEntered;

    @FXML
    private TextField EmailEntered;

    @FXML
    private PasswordField NewPasswordEntered;

    @FXML
    private TextField UsernameEntered;

    @FXML
    void BackHomePageButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"Home Page.fxml","MEOW RAPID TRANSIT");
    }

    @FXML
    void DeleteButton(ActionEvent event) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete account ?");
            alert.setHeaderText("Do you confirm to delete your account ?");
            alert.showAndWait();
            alert.show();
            if (alert.getResult() == ButtonType.OK) {


                try {
                    Connection connection = database.getConnections();
                    String sql = "DELETE FROM `fopgui`.`user`\n" + "WHERE `email` =? ";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, getLoginEmail());
                    if(statement.executeUpdate()==1) {
                        CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "The account " + getLoginEmail() + " is deleted!");
                        new CommonTask().switchScene(event, "User Profile.fxml", "MEOW RAPID TRANSIT");
                        setLoginEmail(null);
                    }else{
                        CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "Fail deleted");
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    database.closeConnections();
                }
            }
            alert.close();
        }


    @FXML
    void SaveButton(ActionEvent event) throws SQLException {
            Connection connection = database.getConnections();
            String newUsername = UsernameEntered.getText();
            String newEmail = EmailEntered.getText();
            String newContactnumber = ContactNumberEntered.getText();
            String newPassword = NewPasswordEntered.getText();
            String newConfirmpassword= ConfirmPasswordEntered.getText();

            if ( newUsername.isEmpty() || newEmail.isEmpty() || newContactnumber.isEmpty() || newPassword.isEmpty() || newConfirmpassword.isEmpty()) {
                CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
            }else if (!newPassword.equals(newConfirmpassword)){
                CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Password and Confirm Password does not match");
            }
            else {
                String sql = "UPDATE `fopgui`.`user` SET `username` = ?,`email` = ?,`password` = ?,`contactnum` = ? WHERE `email` = ?;";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, newUsername);
                preparedStatement.setString(2, newEmail);
                preparedStatement.setString(3, newConfirmpassword);
                preparedStatement.setString(4, newContactnumber);
                preparedStatement.setString(5, getLoginEmail());

                try {
                    if(preparedStatement.executeUpdate()==1) {
                        CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "Your user profile is saved successful");
                        setLoginEmail(newEmail);
                    }else{
                        CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "Failed !! Change unsuccessful !");
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                }finally {
                    database.closeConnections();
                }
            }

        }

}


