module com.example.hcvfuzzy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens com.example.hcvfuzzy to javafx.fxml;
    exports com.example.hcvfuzzy;
}