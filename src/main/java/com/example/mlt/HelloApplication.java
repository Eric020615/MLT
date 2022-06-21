package com.example.mlt;
//import the libraries that used to form the application
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
//tester program to launch the application
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Create an instance of FXMLLoader for switching to the Log In Page
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login Page.fxml"));  
        //Create an instance of Scene
        Scene scene = new Scene(fxmlLoader.load());
        //our application header
        stage.setTitle("MEOW RAPID TRANSIT"); 
        //Set the scene
        stage.setScene(scene);
        //non resizable/auto-adjust size page
        stage.setResizable(false); 
        //show the stage for the page
        stage.show();
    }
    //main method to launch it
    public static void main(String[] args) {
        launch();
    }
}
