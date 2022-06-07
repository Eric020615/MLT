package com.example.mlt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

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

    }

    @FXML
    void SaveButton(ActionEvent event) {

    }

}
