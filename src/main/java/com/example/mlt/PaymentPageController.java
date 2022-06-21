package com.example.mlt;

//import the libraries needed in the class
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.mlt.LoginPageController.getLoginEmail;

//Payment Page Controller
public class PaymentPageController implements Initializable {
    //Create a field name accountBalanceLabel
    @FXML
    public Label accountBalanceLabel;
    //Create a button name HomeIcon
    @FXML
    private Button HomeIcon;
    //Create a button name ProfileIcon
    @FXML
    private Button ProfileIcon;
    //Create an instance for class Double name userBalance
    public static Double userBalance;

    //Create a OnAction method name topUpButtonPressed
    @FXML
    void topUpButtonPressed(ActionEvent event) throws IOException {
        //Create an instance of FXMLLoader for switching to the TopUp Page
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Top Up Page.fxml"));
        //Load the fxml page
        Parent root = fxmlLoader.load();
        //Create an instance of Stage
        Stage stage = new Stage();
        //Set stage title to be Top Up
        stage.setTitle("Top up");
        //Set the scene for the stage
        stage.setScene(new Scene(root));
        //Set the stage to be not resizable
        stage.setResizable(false);
        //Show the stage
        stage.show();

    }
    //show Balance method that throws SQLException
    public void showBalance() throws SQLException {
        //Create a connection with the database
        Connection connection = database.getConnections();
        try {
            //When the connection is not closed
            if (!connection.isClosed()) {
                String sql = "SELECT * FROM `user` WHERE user.email= ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, getLoginEmail());
                ResultSet resultSet = statement.executeQuery();
                //Get the balance for the user and set it to the label in the page
                while (resultSet.next()) {
                    //Get the balance from the database and convert it to double
                    userBalance = Double.valueOf(resultSet.getString("balance"));
                    //Set the text for the label
                    accountBalanceLabel.setText(String.valueOf(String.format("%.2f", userBalance)));
                }
            }
        //catch SQLException
        }catch (SQLException e) {
            //Print the error message
            e.printStackTrace();
        }finally {
            //Close the connection
            connection.close();
        }
    }

    //Method name initialize
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            //Show the balance for the user
            showBalance();
        } catch (SQLException e) {
            //Print the error message
            e.printStackTrace();
        }

    }

    //Create a OnAction method name HomeButton
    @FXML
    void HomeButton(ActionEvent event) throws IOException{
        //Pass the arguments to the switchScene method to switch to Home Page with title MEOW RAPID TRANSIT
        new CommonTask().switchScene(event,"Home Page.fxml","MEOW RAPID TRANSIT");
    }
    //Create a OnAction method name ProfileButton
    @FXML
    void ProfileButton(ActionEvent event) throws IOException {
        //Pass the arguments to the switchScene method to switch to User Profile Page with title MEOW RAPID TRANSIT
        new CommonTask().switchScene(event,"User Profile.fxml","MEOW RAPID TRANSIT");
    }
    //Create a OnAction method name NavigationButton
    @FXML
    void NavigationButton(ActionEvent event) throws IOException{
        //Pass the arguments to the switchScene method to switch to NavigationPage with title MEOW RAPID TRANSIT
        new CommonTask().switchScene(event,"Navigation Page.fxml","MEOW RAPID TRANSIT");
    }
    //Create a OnAction method name HistogramButton
    @FXML
    void HistogramButton(ActionEvent event) throws IOException {
        //Pass the arguments to the switchScene method to switch to Histogram Page with title MEOW RAPID TRANSIT
        new CommonTask().switchScene(event,"Histogram Page.fxml","MEOW RAPID TRANSIT");
    }
    //Create a OnAction method name PaymentPageButton
    @FXML
    void PaymentPageButton(ActionEvent event) throws IOException {
        //Pass the arguments to the switchScene method to switch to Payment Page with title MEOW RAPID TRANSIT
        new CommonTask().switchScene(event,"Payment Page.fxml","MEOW RAPID TRANSIT");
    }

}