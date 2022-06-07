package com.example.mlt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

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
    void SignUpButton(ActionEvent event) {

    }

}

