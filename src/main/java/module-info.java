module com.example.javafx.life {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafx.life to javafx.fxml;
    exports com.example.javafx.life;
}