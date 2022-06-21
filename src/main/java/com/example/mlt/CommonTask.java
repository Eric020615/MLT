package com.example.mlt;
//import all the libraries needed
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class CommonTask extends HelloApplication {
    //Stage, scene and root help to create the GUI page
    private Stage stage;
    private Scene scene;
    private Parent root;

    //A method to switch the scene of page in GUI
    public void switchScene(ActionEvent event , String fxmlfile, String title) throws IOException {
        //Root to load fxml source
        root = FXMLLoader.load(getClass().getResource(fxmlfile));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        //scene to get the root(fxml resource)
        scene = new Scene(root);
        //set the scene to stage and show
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(title);
        stage.show();

    }

    //Pop out the appropriate message when occuring the exception
    public static void showAlert(Alert.AlertType type, String title, String header){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.show();
    }

}
