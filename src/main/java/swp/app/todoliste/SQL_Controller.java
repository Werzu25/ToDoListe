package swp.app.todoliste;

import java.sql.*;

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

    public static void insertEntry() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customers (first-name,second_name,email) values ( ?, ?, ?)");
            preparedStatement.setString(1,first_name);
            preparedStatement.setString(2,second_name);
            preparedStatement.setString(3,email);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("There was an error in the SQL syntax");
        }
    }
    public static void updateTask() {

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
    }
}
