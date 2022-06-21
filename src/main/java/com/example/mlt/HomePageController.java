package com.example.mlt;
//Import the libraries that required in this module
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
//Home Page Controller
public class HomePageController {
    //On Action to move to Histogram Page with title: MEOW RAPID TRANSIT
    @FXML
    void HistogramButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"Histogram Page.fxml","MEOW RAPID TRANSIT");
    }
    //On Action to move to Navigation Page with title: MEOW RAPID TRANSIT
    @FXML
    void NavigationButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"Navigation Page.fxml","MEOW RAPID TRANSIT");
    }
    //On Action to move to Payment Page with title: MEOW RAPID TRANSIT
    @FXML
    void PaymentButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"Payment Page.fxml","MEOW RAPID TRANSIT");
    }
    //On Action to move to User Profile Page with title: MEOW RAPID TRANSIT
    @FXML
    void ProfileButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"User Profile.fxml","MEOW RAPID TRANSIT");
    }

}
