package Server_Core.Database;

import Application_Server_Interface.Setup.Read_db_properties;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class Manage_db {
    private String db_user_name;
    private String db_password;
    protected String db_url;
    private boolean table_init = false;

    public Manage_db() throws IOException, SQLException {
        String user_dir = Read_db_properties.get_user_dir(true);
        String db_config_path = user_dir + "/db_config.properties";
        Read_db_properties rpf = new Read_db_properties(db_config_path);

        // variables for the users database
        String db_name = rpf.get_db_name();
        db_url = "jdbc:postgresql://localhost:5432/" + db_name;
        db_user_name = rpf.get_db_user_name();
        db_password = rpf.get_db_password();

        search_for_sql_drivers();
    }

    protected boolean init_table(String table_name, String sql_create_table) throws SQLException {
        Connection db_conn = get_db_connection();
        boolean table_exists = check_table_exists(db_conn, table_name);
        if (!table_exists){
            create_table(db_conn, table_name, sql_create_table);
        }
        db_conn.close();
        this.table_init = true;
        return table_exists;
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

    protected String execute_query_with_gson(String sql_query, String exception_msg, String[] rs_strings) throws SQLException {
        String[][] string_arr = execute_query(sql_query, exception_msg, rs_strings);
        return convert_string_arr_to_gson(string_arr);
    }

    protected String convert_string_arr_to_gson(String[][] string_arr){
        Gson gson = new GsonBuilder().create();
        return gson.toJson(string_arr);
    }

    protected String[][] execute_query(String sql_query, String exception_msg, String[] rs_strings) throws SQLException {
        assert table_init : "table_init has not been ran";
        Connection conn = get_db_connection();
        ResultSet rs;
        String[][] string_arr;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = s.executeQuery(sql_query);
            string_arr = get_string_arr_from_rs(rs, rs_strings);
            s.close();
        } catch (Exception e) {
            System.out.println("Failed to convert rs to string array. Check rs_strings are correct.");
            e.printStackTrace();
            throw new SQLException(exception_msg);
        }
        conn.close();
        return string_arr;
    }

    private String[][] get_string_arr_from_rs(ResultSet rs, String[] rs_strings) throws SQLException {
        // Used to get size of result set https://stackoverflow.com/questions/192078/how-do-i-get-the-size-of-a-java-sql-resultset
        int last_row = 0;
        if (rs.last()){
            last_row = rs.getRow();
            rs.beforeFirst();
        }
        int row_count = 0;
        String[][] string_arr = new String[last_row][rs_strings.length];
        while (rs.next()){
            for (int i = 0; i < rs_strings.length; i++){
                string_arr[row_count][i] = rs.getString(rs_strings[i]);
            }
            row_count += 1;
        }
        return string_arr;
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