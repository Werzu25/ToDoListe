package swp.app.todoliste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL_Controller {

    private static Database db; //leons db
    public static void initConnection(String url) {
        try {
            db = new Database(url);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection to SQLite has not been established.");
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

    }
    public static void updateEntry() {

    }
    public static void deleteEntry() {

    }

    public static void createDB(){
        try {
            db.executeStatement("CREATE TABLE IF NOT EXISTS tasks (id INTEGER PRIMARY KEY, " +
                    "task TEXT, " +
                    "due_date TEXT, " +
                    "priority INTEGER)"); // mehr spalten selber einfügen leon leck meine eier
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }
}
