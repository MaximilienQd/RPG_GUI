module com.example.rpg_gui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rpg_gui to javafx.fxml;
    exports com.example.rpg_gui;
}