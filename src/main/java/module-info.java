module swp.app.todoliste {
    requires javafx.controls;
    requires javafx.fxml;


    opens swp.app.todoliste to javafx.fxml;
    exports swp.app.todoliste;
}