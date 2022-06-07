package com.example.mlt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class RegisterPageController {

    private Stage stage;

    private Scene scene;

    private Parent root;

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
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login Page.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    void SignUpButton(ActionEvent event) {

    }
}
