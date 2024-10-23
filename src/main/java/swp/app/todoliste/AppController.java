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
    int choosenAssignment;
    @FXML
    Pane p_root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SQL_Controller.initConnection("jdbc:mysql://localhost:3306/swp_202425","user2","MySQLDB5");
        switchToAllAssignments();
    }
    public void updateAssignment(){
        p_root.getChildren().remove(0);
        VBox vbox = new VBox();
        //bind VBox height und width to o_root
        vbox.prefWidthProperty().bind(p_root.widthProperty());
        vbox.prefHeightProperty().bind(p_root.heightProperty());
        //get the current values of this task
        Task task = SQL_Controller.getOneTask(choosenAssignment);
        //add fields to update a task
        vbox.getChildren().add(new Label("number o task: "));
        TextField tf_number = new TextField(String.valueOf(task.getId()));
        tf_number.setDisable(true);
        vbox.getChildren().add(tf_number);
        vbox.getChildren().add(new Label("name task: "));
        TextField tf_name = new TextField(task.getTask());
        vbox.getChildren().add(tf_name);
        vbox.getChildren().add(new Label("up to:"));
        DatePicker datePicker = new DatePicker(task.getChangeDate());
        vbox.getChildren().add(datePicker);
        //set submit button action to update task in DB
        Button submit = new Button("Submit");
        submit.setOnAction(t -> {
            //update values of task
            task.setTask(tf_name.getText().toString());
            task.setChangeDate(datePicker.getValue());
            //update task in DB
            SQL_Controller.updateTask(task);
            //switch to task-overview
            p_root.getChildren().remove(0);
            switchToAllAssignments();
        });
        vbox.getChildren().add(submit);
        //back to overview button
        vbox.getChildren().add(new Label(" "));
        Button back = new Button("back to overview");
        back.setOnAction(e ->{
            p_root.getChildren().remove(0);
            switchToAllAssignments();
        });
        vbox.getChildren().add(back);
        //add vbox to p_root
        p_root.getChildren().add(vbox);
    }
    public void addAssignment(){
        p_root.getChildren().remove(0);
        VBox vbox = new VBox();
        //bind VBox height und width to o_root
        vbox.prefWidthProperty().bind(p_root.widthProperty());
        vbox.prefHeightProperty().bind(p_root.heightProperty());
        //add fields to insert a task
        vbox.getChildren().add(new Label("name task: "));
        TextField tf_name = new TextField();
        vbox.getChildren().add(tf_name);
        vbox.getChildren().add(new Label("up to:"));
        DatePicker datePicker = new DatePicker();
        vbox.getChildren().add(datePicker);
        Button submit = new Button("Submit");
        submit.setOnAction(e->{
            //insert task to DB
            Task newTask = new Task(tf_name.getText().toString(),datePicker.getValue());
            SQL_Controller.insertTask(newTask);
            //switch to task-overview
            p_root.getChildren().remove(0);
            switchToAllAssignments();
        });
        vbox.getChildren().add(submit);
        //back to overview button
        vbox.getChildren().add(new Label(" "));
        Button back = new Button("back to overview");
        back.setOnAction(e ->{
            p_root.getChildren().remove(0);
            switchToAllAssignments();
        });
        vbox.getChildren().add(back);
        //add vbox to p_root
        p_root.getChildren().add(vbox);
    }
    public void switchToAllAssignments(){
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
        Task[] allTasks = SQL_Controller.selectTask();
        for (int i = 0; i < allTasks.length; i++) {
            RadioButton radioB = new RadioButton();
            radioB.setToggleGroup(radioButtonGroup);
            radioB.setText(allTasks[i].toString());
            radioB.setAccessibleText(String.valueOf(allTasks[i].getId()));
            radioB.setOnAction(e -> {
                System.out.println(radioB.getAccessibleText());
                choosenAssignment = Integer.valueOf(radioB.getAccessibleText());
                b_edit.setDisable(false);
                b_delete.setDisable(false);
            });
            assignmentBox.getChildren().add(radioB);
        }
        //set button action
        b_add.setOnAction(e->{addAssignment();});
        b_delete.setOnAction(e ->{
            Task choosenTask = SQL_Controller.getOneTask(choosenAssignment);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure to delete the task "+choosenTask.getId()+"-"+choosenTask.getTask()+" ?");
            alert.show();
            alert.setOnCloseRequest(f ->{
                System.out.println(choosenAssignment);
                SQL_Controller.deleteTask(choosenAssignment);
                p_root.getChildren().remove(0);
                switchToAllAssignments();
            });
        });
        b_edit.setOnAction(e -> {
            updateAssignment();
        });
        //set buttons disable when no assignment is selected
        b_delete.setDisable(true);
        b_edit.setDisable(true);
    }
}