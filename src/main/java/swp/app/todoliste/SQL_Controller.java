package swp.app.todoliste;

import java.sql.*;
import java.time.LocalDate;

public class SQL_Controller {

    public static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    public static void initConnection(String url) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customers (id,task,due_date) values ( ?, ?, ?)");
            preparedStatement.setInt(1,task.getId());
            preparedStatement.setString(2,task.getTask());

            LocalDate localDate = task.getChangeDate();
            Date insetedDate = Date.valueOf(localDate);
            preparedStatement.setDate(3,insetedDate);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("There was an error in the SQL syntax");
        }
    }
    public static void updateTask(Task task) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tasks SET title = ?, changeDate = ? WHERE id = ?");
            preparedStatement.setString(1,task.getTitle() );
            preparedStatement.setDate(2, Date.valueOf(task.getChangeDate()));
            preparedStatement.setInt(3,task.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException) {
            System.out.println("There was an error in the SQL syntax");
        }

    }
    public static void deleteTask(Task task) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tasks WHERE id = ?");
            preparedStatement.setInt(1, task.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException) {
            System.out.println("There was an error in the SQL syntax");
        }
    }

    public static Task[] selectTask() {
        try {
            resultSet = statement.executeQuery("SELECT * FROM tasks");
        } catch (SQLException sqlException) {

        }
        return null;
    }
}
