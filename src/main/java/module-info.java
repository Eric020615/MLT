module com.example.mlt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mlt to javafx.fxml;
    exports com.example.mlt;
}