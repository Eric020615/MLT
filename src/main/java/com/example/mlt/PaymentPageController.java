package com.example.mlt;

import javafx.application.Platform;
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


public class PaymentPageController implements Initializable {

    public static Stage Paymentstage;

    @FXML
    public Label accountBalanceLabel;

    @FXML
    private Button HomeIcon;

    @FXML
    private Button ProfileIcon;

    public static Double userBalance;


    @FXML
    void topUpButtonPressed(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Top Up Page.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Top up");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();

    }

    public void showBalance() throws SQLException {
        Connection connection = database.getConnections();
        try {
            if (!connection.isClosed()) {
                String sql = "SELECT * FROM `user` WHERE user.email= ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, getLoginEmail());
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    userBalance = Double.valueOf(resultSet.getString("balance"));
                    accountBalanceLabel.setText(String.valueOf(String.format("%.2f", userBalance)));
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connection.close();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showBalance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void HomeButton(ActionEvent event) throws IOException{
        new CommonTask().switchScene(event,"Home Page.fxml","MEOW RAPID TRANSIT");
    }

    @FXML
    void ProfileButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"User Profile.fxml","MEOW RAPID TRANSIT");
    }

    @FXML
    void NavigationButton(ActionEvent event) throws IOException{
        new CommonTask().switchScene(event,"Navigation Page.fxml","MEOW RAPID TRANSIT");
    }

    @FXML
    void HistogramButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"Histogram Page.fxml","MEOW RAPID TRANSIT");
    }

    @FXML
    void PaymentPageButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"Payment Page.fxml","MEOW RAPID TRANSIT");
    }

}