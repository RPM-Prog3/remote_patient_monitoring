package Database;

import Setup.Read_db_properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class Manage_db {
    private String db_user_name;
    private String db_password;
    protected String db_url;
    private boolean table_init = false;

    public Manage_db() throws IOException, SQLException {
        String user_dir = Read_db_properties.get_user_dir();
        System.out.println(user_dir);
        String db_config_path = user_dir + "/db_config.properties";

        Read_db_properties rpf = new Read_db_properties(db_config_path);

        // variables for the users database
        String db_name = rpf.get_db_name();
        db_url = "jdbc:postgresql://localhost:5432/" + db_name;
        db_user_name = rpf.get_db_user_name();
        db_password = rpf.get_db_password();

        search_for_sql_drivers();
    }

    protected void init_table(String table_name, String sql_create_table) throws SQLException {
        Connection db_conn = get_db_connection();
        boolean table_exists = check_table_exists(db_conn, table_name);
        if (!table_exists){
            create_table(db_conn, table_name, sql_create_table);
        }
        db_conn.close();
        this.table_init = true;
    }

    protected Connection get_db_connection() throws SQLException {
        Connection db_conn;
        try {
            db_conn = DriverManager.getConnection(db_url, db_user_name, db_password);
        } catch (Exception e) {
            System.out.println("failed to connected");
            e.printStackTrace();
            throw new SQLException(String.format("Unable to connect to database: %s", db_url));
        }
        return db_conn;
    }

    protected void execute_update(String sql_update, String exception_msg) throws SQLException {
        assert table_init : "table_init has not been ran";
        Connection conn = get_db_connection();
        try {
            Statement s = conn.createStatement();
            s.executeUpdate(sql_update);
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException(exception_msg);
        }
        conn.close();
    }

    protected ResultSet execute_query(String sql_query, String exception_msg) throws SQLException {
        assert table_init : "table_init has not been ran";
        Connection conn = get_db_connection();
        ResultSet rs;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = s.executeQuery(sql_query);
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException(exception_msg);
        }
        conn.close();
        return rs;
    }

    private boolean check_table_exists(Connection conn, String table_name) throws SQLException {
        DatabaseMetaData db_metadata = conn.getMetaData();
        ResultSet tables = db_metadata.getTables(null, null, table_name, null);
        return tables.next();
    }

    private void create_table(Connection conn, String table_name, String sql_create_table) throws SQLException {
        try {
            Statement s = conn.createStatement();
            s.executeUpdate(sql_create_table);
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(String.format("Failed to create new table: %s", table_name));
        }
        boolean exists = check_table_exists(conn, table_name);
        if (!exists){
            throw new SQLException(String.format("Failed to create new table: %s", table_name));
        }
    }

    private void search_for_sql_drivers() throws FileNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            System.out.println("Failed to find drivers");
            throw new FileNotFoundException(String.format("Unable to find drivers for: %s", db_url));
        }
    }
}
