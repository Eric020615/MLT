module com.example.mlt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.mlt to javafx.fxml;
    exports com.example.mlt;
}