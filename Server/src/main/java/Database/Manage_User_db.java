package Database;

import Data.User;
import Setup.Read_db_properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class Manage_User_db {
    private String db_name, db_user_name, db_password;
    private String db_url;
    private String table_name;

    public Manage_User_db() throws IOException, SQLException {
        String user_dir = Read_db_properties.get_user_dir();
        String db_config_path = user_dir + "/Server/db_config.properties";

        table_name = "users";
        Read_db_properties rpf = new Read_db_properties(db_config_path);

        // variables for the users database
        db_name = rpf.get_db_name();
        db_url = "jdbc:postgresql://localhost:5432/" + db_name;
        db_user_name = rpf.get_db_user_name();
        db_password = rpf.get_db_password();

        search_for_sql_drivers();

        Connection db_conn = get_db_connection();

        boolean table_exists = check_table_exists((db_conn));
        System.out.println(table_exists);
        if (!table_exists){
            create_table(db_conn);
        }
        db_conn.close();
    }

    public void add_user(User u) throws SQLException {
        String username = u.get_username();
        String password = u.get_password();
        add_user(username, password);
    }

    public void add_user(String username, String password) throws SQLException {
        Connection db_conn = get_db_connection();
        add_user(db_conn, username, password);
        db_conn.close();
    }

    public void add_user(Connection conn, String username, String password) throws SQLException {
        String sql_add_user = String.format("insert into %s (username, password) values('%s', '%s')",
                username, password);
        String exception_msg = "Unable to add user to database - table: %s - %s".format(db_url, table_name);
        execute_query(conn, sql_add_user, exception_msg);
    }

    public boolean find_user(User check_user){
        System.out.println("find_user: need to add logic to check if user in db");
        return true;
    }

    private void execute_query(Connection conn, String sql_query, String exception_msg) throws SQLException {
        try {
            Statement s = conn.createStatement();
            s.executeUpdate(sql_query);
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException(exception_msg);
        }
    }

    private boolean check_table_exists(Connection conn) throws SQLException {
        DatabaseMetaData db_metadata = conn.getMetaData();
        ResultSet tables = db_metadata.getTables(null, null, table_name, null);
        if (tables.next()) {
            return true;
        }
        return false;
    }

    private void create_table(Connection conn) throws SQLException {
        try {
            Statement s = conn.createStatement();
            String sql_create_table = String.format("create table %s (\n" +
                    "   id SERIAL PRIMARY KEY,\n" +
                    "   username varchar(128) NOT NULL,\n" +
                    "   password varchar(128) NOT NULL,\n" +
                    ");", table_name);
            s.executeUpdate(sql_create_table);
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to create new table: %s".format(table_name));
        }
        boolean exists = check_table_exists(conn);
        if (!exists){
            throw new SQLException("Failed to create new table: %s".format(table_name));
        }
    }

    private void search_for_sql_drivers() throws FileNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            System.out.println("Failed to find drivers");
            throw new FileNotFoundException("Unable to find drivers for: %s".format(db_url));
        }
    }

    private Connection get_db_connection() throws SQLException {
        Connection db_conn = null;
        try {
            db_conn = DriverManager.getConnection(db_url, db_user_name, db_password);
        } catch (Exception e) {
            System.out.println("failed to connected");
            e.printStackTrace();
            throw new SQLException("Unable to connect to database: %s".format(db_url));
        }
        return db_conn;
    }

}
