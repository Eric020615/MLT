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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login Page.fxml"));  //Open the Login Page
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("MEOW RAPID TRANSIT"); //our application header
        stage.setScene(scene);
        stage.setResizable(false); //non resizable/auto-adjust size page
        stage.show();//show the page
    }

    public static void main(String[] args) {
        launch(); //main method to launch it
    }
}
