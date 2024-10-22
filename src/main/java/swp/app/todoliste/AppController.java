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
    Pane p_root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switchToAllAssignments();
        SQL_Controller.initConnection("jdbc:mysql://localhost:3306/","user2","MySQLDB5");
    }
    public void addAssignment(){
        p_root.getChildren().remove(0);
        VBox vbox = new VBox();
        //bind VBox height und width to o_root
        vbox.prefWidthProperty().bind(p_root.widthProperty());
        vbox.prefHeightProperty().bind(p_root.heightProperty());
        //add fields to insert a task
        vbox.getChildren().add(new Label("name task: "));
        TextField tf_name = new TextField("insert name");
        vbox.getChildren().add(tf_name);
        vbox.getChildren().add(new Label("up to:"));
        DatePicker datePicker = new DatePicker();
        vbox.getChildren().add(datePicker);
        Button submit = new Button("Submit");
        submit.setOnAction(e->{
            //insert task to DB
            Task newTask = new Task(tf_name.getText().toString(),datePicker.getValue());
            SQL_Controller.insertTask(newTask);
        });
        vbox.getChildren().add(submit);
        vbox.getChildren().add(new Label(" "));
        Button back = new Button("back to overview");
        back.setOnAction(e ->{
            switchToAllAssignments();
        });
        vbox.getChildren().add(back);

        p_root.getChildren().add(vbox);
    }
    public void switchToAllAssignments(){
        p_root.getChildren().removeAll();
        SplitPane splitPane = new SplitPane();
        //bind splitPane height und width to p_root
        splitPane.prefWidthProperty().bind(p_root.widthProperty());
        splitPane.prefHeightProperty().bind(p_root.heightProperty());
        p_root.getChildren().add(splitPane);
        VBox assignmentBox = new VBox();
        assignmentBox.setMinSize(p_root.getMaxWidth(),p_root.getMaxHeight());
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
        b_add.setOnAction(e->{addAssignment();
            System.out.println("clicked");});
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