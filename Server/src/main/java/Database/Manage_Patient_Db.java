package Database;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Manage_Patient_Db {
    private String patients_list_dbUrl;

    public Manage_Patient_Db() throws FileNotFoundException, SQLException {
        patients_list_dbUrl = "jdbc:postgresql://localhost:5432/postgres";
        String table_name = "patients";
        search_for_drivers();
        Connection conn = get_connection("postgres", "admin");

        boolean table_exists = check_table_exists(conn, table_name);
        //if (!table_exists){
        //    create_table(table_name);
        //}
        //conn.close();
    }

    public boolean check_table_exists(Connection conn, String table_name) throws SQLException {
        try {
            Statement s = conn.createStatement();
            String select_table = String.format("SELECT to_regclass('public.%s');", table_name);
            s.executeUpdate(select_table);
            s.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("hi");
            System.out.println(e);
        }
        return false;
    }

    public void create_table(String table_name){

    }

    public void add_patient(Connection conn, String table_name,
                            String family_name, String given_name,
                            String date_of_birth, String email,
                            String phone_number) throws SQLException {
        try {
            Statement s = conn.createStatement();
            String sqlStr = String.format("insert into %s (familyname, givenname, dofbirth, email, phonenumber) values('%s', '%s', '%s', '%s', '%s');", table_name, family_name, given_name, date_of_birth, email, phone_number);
            s.executeUpdate(sqlStr);
            s.close();
            conn.close();
        } catch (Exception e) {
            throw new SQLException("Unable to add patient to database: %s".format(patients_list_dbUrl));
        }
    }

    public void search_for_drivers() throws FileNotFoundException {
        try {
            // Registers the driver
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            System.out.println("Failed to find drivers");
            throw new FileNotFoundException("Unable to find drivers for: %s".format(patients_list_dbUrl));
        }
    }

    public Connection get_connection(String username, String password) throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(patients_list_dbUrl, username, password);
        } catch (Exception e) {
            System.out.println("failed to connected");
            throw new SQLException("Unable to connect to database: %s".format(patients_list_dbUrl));
        }
        return conn;
    }

}
