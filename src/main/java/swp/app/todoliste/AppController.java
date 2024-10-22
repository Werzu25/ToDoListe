package swp.app.todoliste;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppController implements Initializable{
    String choosenAssignment;
    @FXML
    Pane P_root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switchToAllAssignments();

    }
    public void addAssignment(){
        P_root.getChildren().removeAll();

    }
    public void switchToAllAssignments(){
        P_root.getChildren().removeAll();
        SplitPane splitPane = new SplitPane();
        P_root.getChildren().add(splitPane);
        VBox assignmentBox = new VBox();
        VBox buttonBox = new VBox();
        splitPane.getItems().addAll(assignmentBox,buttonBox);
        Button b_add = new Button("Aufgabe hinzufügen");
        Button b_edit = new Button("Aufgabe bearbeiten");
        Button b_delete = new Button("Aufgabe löschen");
        buttonBox.getChildren().addAll(b_add,b_edit,b_delete);
        //Toggle Group for RadioButtons, just that one could be selected
        ToggleGroup radioButtonGroup = new ToggleGroup();
        //put Assignments into Assignment-Overview
        for (int i = 0; i < 5; i++) {
            String name = "Aufgabe "+i;
            RadioButton cBox = new RadioButton();
            //CheckBox cBox = new CheckBox();
            cBox.setToggleGroup(radioButtonGroup);
            cBox.setText(name);
            cBox.setOnAction(e -> {
                choosenAssignment = name;
                b_edit.setDisable(false);
                b_delete.setDisable(false);
            });
            assignmentBox.getChildren().add(cBox);
        }
        //set button action
        b_add.setOnAction(e -> {
            try{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Aufgabe adden "+choosenAssignment);
                alert.show();
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        });
        b_delete.setOnAction(e ->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Aufgabe löschen "+choosenAssignment);
            alert.show();
        });
        b_edit.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Aufgabe updaten "+choosenAssignment);
            alert.show();
        });
        //set buttons disable when no assignment is selected
        b_delete.setDisable(true);
        b_edit.setDisable(true);
    }
}