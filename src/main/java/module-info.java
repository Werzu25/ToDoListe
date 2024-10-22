module swp.app.todoliste {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens swp.app.todoliste to javafx.fxml;
    exports swp.app.todoliste;
}