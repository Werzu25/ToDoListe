package swp.app.todoliste;

import java.sql.*;

public class Database {
    private Connection c;
    private ResultSet rs = null;
    private Statement stmt;
    private String url;
    private PreparedStatement prst;

    public Database (String URL) throws SQLException, ClassNotFoundException{
        this.url=URL;

        Class.forName("com.mysql.cj.jdbc.Driver");
        c = DriverManager.getConnection(url);
        c.setAutoCommit(false);
        System.out.println("\tOpened database successfully");
        System.out.println("+-----------------------------------+");

        stmt = c.createStatement();
    }

    public void dropTable (String table) throws SQLException {
        String sql = "DROP TABLE IF EXISTS " + table;
        stmt.executeUpdate(sql);
    }

    public void executeStatement (String sql) throws SQLException{
        stmt.executeUpdate(sql);
    }

    public ResultSet executeStatementRST (String sql) throws SQLException{
        ResultSet rst = stmt.executeQuery(sql);
        return rst;
    }

    public Connection getC() {
        return c;
    }
    public void setC(Connection c) {
        this.c = c;
    }
    public ResultSet getRs() {
        return rs;
    }
    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
    public Statement getStmt() {
        return stmt;
    }
    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public PreparedStatement getPrst() {
        return prst;
    }

    public void setPrst(PreparedStatement prst) {
        this.prst = prst;
    }
}