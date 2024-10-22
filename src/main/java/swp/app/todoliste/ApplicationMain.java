package swp.app.todoliste;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationMain.class.getResource("app-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 300);
        stage.setTitle("ToDo-App");
        stage.getIcons().add(new Image(ApplicationMain.class.getResourceAsStream("/icon.jpg")));
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}