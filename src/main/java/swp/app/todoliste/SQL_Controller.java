package swp.app.todoliste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQL_Controller {

    public static Connection connection = null;
    public static void initConnection(String url) {
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
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
    public static void updateEntry() {

    }
    public static void deleteEntry() {

    }

    public static void selectEntry() {

    }
}
