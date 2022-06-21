package com.example.mlt;
//import all the libraries needed
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {


    private static String loginEmail;

    @FXML
    private PasswordField PasswordLoginText;

    @FXML
    private TextField EmailLoginText;

    @FXML
    private Text PromptLoginLabel;
    //Method to get login email
    public static String getLoginEmail(){
        return loginEmail;
    }
    //Method to set login email
    public static void setLoginEmail(String newEmail){
        loginEmail=newEmail;
    }

    @FXML
    //A method to sign in (login function)
    void SignInButton(ActionEvent event) throws IOException {
        //Get connection to mysql and get information of user email and password  from user
        Connection connection = database.getConnections();
        String userEmail =  EmailLoginText.getText();
        String userPassword =PasswordLoginText.getText();
        try {
            //Check the email and password field whether they are empty , prompt error label if they are empty
            if (userEmail.isEmpty() == true || userPassword.isEmpty() == true) {
                PromptLoginLabel.setVisible(true);
            //Check the format email correct or not
            } else if(!EmailLoginText.getText().matches("^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@+[A-Za-z0-9-]+([.][A-Za-z0-9]+)")) {
                CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Invalid Email ! Make sure your Email format is correct.");
            }else{
                //Get user information from mysql database to check the validation of user information
                String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, userEmail);
                preparedStatement.setString(2, userPassword);
                ResultSet resultSet = preparedStatement.executeQuery();
                //Check the result. If result has the message means the infor is valid to login, otherwise it is empty means the user infor get wrong
                if (resultSet.next()) {
                    CommonTask.showAlert(Alert.AlertType.INFORMATION, "Login Success!", "Successfully Logged In!");
                    setLoginEmail(userEmail);
                    new CommonTask().switchScene(event,"Home Page.fxml","MEOW RAPID TRANSIT");
                } else {
                    CommonTask.showAlert(Alert.AlertType.ERROR, "Login Failed!", "Incorrect Email or Password!");
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            database.closeConnections();
        }

    }

    //Action on button to switch to Sign Up Page
    @FXML
    void SignUpButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"Sign Up Page.fxml","MEOW RAPID TRANSIT");
    }
    //Pop out the window of forgot password page
    @FXML
    void ForgotPasswordButton(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ForgotPassword Page.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Change Password");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();

    }
    //Set the error information is invisible at the beginning
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PromptLoginLabel.setVisible(false);
    }
}

