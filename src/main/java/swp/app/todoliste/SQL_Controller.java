package swp.app.todoliste;

import java.sql.*;
import java.time.LocalDate;

public class SQL_Controller {

    public static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    public static void initConnection(String url, String user, String passwd) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,passwd);
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public static void insertTask(Task task) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tasks (task,due_date,done) values (?, ?,?)");
            preparedStatement.setString(1,task.getTask());
            preparedStatement.setDate(2,Date.valueOf(task.getChangeDate()));
            preparedStatement.setBoolean(3,task.isDone());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("There was an error in the SQL syntax");
        }
    }
    public static void updateTask(Task task) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tasks SET task = ?, due_date = ?, done = ? WHERE id = ?");
            preparedStatement.setString(1,task.getTask() );
            preparedStatement.setDate(2, Date.valueOf(task.getChangeDate()));
            preparedStatement.setBoolean(3,task.isDone());
            preparedStatement.setInt(4,task.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException) {
            System.out.println("There was an error in the SQL syntax");
        }

    }
    public static void updateDoneStatus(Task task){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tasks SET done = ? WHERE id = ?");
            preparedStatement.setBoolean(1,true);
            preparedStatement.setInt(2,task.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException){
            System.out.println("There was an error in the SQL syntax");
        }
    }
    public static void deleteTask(int id) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tasks WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException) {
            System.out.println("There was an error in the SQL syntax");
        }
    }

    public static Task[] selectTask() {
        try {
            resultSet = statement.executeQuery("SELECT count(*) FROM tasks");
            resultSet.next();
            Task[] resultTasks = new Task[resultSet.getInt(1)];
            resultSet = statement.executeQuery("SELECT * FROM tasks");
            for (int i = 0; i < resultTasks.length; i++) {
                resultSet.next();
                resultTasks[i] = new Task(resultSet.getString(2),resultSet.getDate(3).toLocalDate(),resultSet.getBoolean(4));
                resultTasks[i].setId(resultSet.getInt(1));
            }
            return resultTasks;
        } catch (SQLException sqlException) {
            System.out.println("There was an error in the SQL syntax");
        }
        return null;
    }
    public static Task getOneTask(int id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tasks WHERE id =?");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Task resultTask = new Task(resultSet.getString(2),resultSet.getDate(3).toLocalDate(),resultSet.getBoolean(4));
            resultTask.setId(resultSet.getInt(1));
            return resultTask;
        } catch (SQLException sqlException) {
            System.out.println("There was an error in the SQL syntax");
        }
        return null;
    }
}
