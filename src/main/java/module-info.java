module com.example.hcvfuzzy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens com.example.hcvfuzzy to javafx.fxml;
    exports com.example.hcvfuzzy;
    exports com.example.hcvfuzzy.FillingMethods;
    opens com.example.hcvfuzzy.FillingMethods to javafx.fxml;
    opens com.example.hcvfuzzy.Controllers to javafx.fxml;
    opens com.example.hcvfuzzy.Database to javafx.fxml;
    opens com.example.hcvfuzzy.Constructors to javafx.fxml;
    exports com.example.hcvfuzzy.Constructors;
    exports com.example.hcvfuzzy.Controllers;
}