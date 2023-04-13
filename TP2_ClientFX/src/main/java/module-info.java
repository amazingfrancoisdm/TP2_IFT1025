module com.example.tp2_clientfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tp2_clientfx to javafx.fxml;
    exports com.example.tp2_clientfx;
}