package com.example.mlt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginPageController {

    private Stage stage;

    private Scene scene;

    private Parent root;

    @FXML
    private PasswordField PasswordLoginText;

    @FXML
    private TextField EmailLoginText;

    @FXML
    private Text PromptLoginLabel;

    @FXML
    void SignInButton(ActionEvent event) throws IOException {
        PromptLoginLabel.setVisible(false);
        if (!PasswordLoginText.getText().isBlank() && !EmailLoginText.getText().isBlank()) {
            //  if email and password entered is not empty,
            // Check the credentials entered by the user
            //if(validateLogin()) {
            if (true){
                // if valid,
                // Forward user to the homepage the credentials matches
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home-page.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } else {
            PromptLoginLabel.setVisible(true);
        }
    }

    @FXML
    void SignUpButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignUp Page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("MEOW RAPID TRANSIT");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    /*
    public boolean validateLogin() {

        Connection connectDB = null;
        Statement statement = null;
        ResultSet queryResult = null;

        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            connectDB = connectNow.getConnection();
            statement = connectDB.createStatement();
            String email = emailEntered_Login.getText();
            String password = passwordEntered_Login.getText();
            queryResult = statement.executeQuery("SELECT * FROM user_account WHERE email = '" + email + "' AND password ='" + password + "'");
            // if the query result is not empty
            if (queryResult.next()) {
                String retrievedEmail = queryResult.getString("email");
                String retrievedPassword = queryResult.getString("password");
                if (retrievedEmail.equals(email) && retrievedPassword.equals(password)) {
                    // if the credentials matches
                    //store current user's info in the User class
                    User.setUsername(queryResult.getString("username"));
                    User.setEmail(retrievedEmail);
                    User.setPassword(retrievedPassword);
                    User.setAddress(queryResult.getString("address"));
                    User.setPaymentPassword(queryResult.getString("paymentPassword"));
                    if (queryResult.getString("balance") == null) {
                        User.setBalance(0);
                    } else {
                        User.setBalance(Double.parseDouble(queryResult.getString("balance")));
                    }
                    return true;

                } else {
                    // if the credentials does not match
                    // display error pop-up message
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid credentials. Please re-enter a valid credentials.");
                    alert.showAndWait();
                    return false;
                }
            } else {
                // if the query result is empty
                // display error pop-up message
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid credentials. Please re-enter a valid credentials.");
                alert.showAndWait();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            if (queryResult != null) {
                try {
                    queryResult.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connectDB != null) {
                try {
                    connectDB.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
     */


}