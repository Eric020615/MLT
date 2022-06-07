package com.example.mlt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class HomePageController {

    @FXML
    void HistogramButton(ActionEvent event) {

    }

    @FXML
    void NavigationButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"Navigation Page.fxml","MEOW RAPID TRANSIT");
    }

    @FXML
    void PaymentButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"Payment Page.fxml","MEOW RAPID TRANSIT");
    }

    @FXML
    void ProfileButton(ActionEvent event) throws IOException {
        new CommonTask().switchScene(event,"User Profile.fxml","MEOW RAPID TRANSIT");
    }

}
