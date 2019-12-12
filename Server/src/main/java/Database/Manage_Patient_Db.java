package Database;

import Setup.Read_Property_File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class Manage_Patient_Db {
    private String db_name;
    private String patients_list_dbUrl;
    private String table_name;
    private String user_name, password;

    public Manage_Patient_Db() throws IOException, SQLException {
        table_name = "patients";
        Read_Property_File rpf = new Read_Property_File();

        // variables for the users database
        db_name = rpf.get_db_name();
        patients_list_dbUrl = "jdbc:postgresql://localhost:5432/" + db_name;
        user_name = rpf.get_db_user_name();
        password = rpf.get_db_password();

        search_for_drivers();

        Connection conn = get_connection();

        boolean table_exists = check_table_exists(conn);
        System.out.println((table_exists));
        if (!table_exists){
            create_table(conn);
        }
        conn.close();
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
        System.out.println("Making table");
        try {
            Statement s = conn.createStatement();
            String sql_create_table = String.format("create table %s (\n" +
                    "   id SERIAL PRIMARY KEY,\n" +
                    "   familyname varchar(128) NOT NULL,\n" +
                    "   givenname varchar(128) NOT NULL,\n" +
                    "   dofbirth varchar(128) NOT NULL,\n" +
                    "   email varchar(128) NOT NULL,\n" +
                    "   phonenumber varchar(32)\n" +
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

    public void add_single_patient(String family_name, String given_name,
                                   String date_of_birth, String email,
                                   String phone_number) throws SQLException {
        Connection conn = get_connection();
        add_patient(conn,
                family_name, given_name,
                date_of_birth, email,
                phone_number);
        conn.close();
    }

    private void add_patient(Connection conn,
                            String family_name, String given_name,
                            String date_of_birth, String email,
                            String phone_number) throws SQLException {
        try {
            Statement s = conn.createStatement();
            String sql_add_patient = String.format("insert into %s (familyname, givenname, dofbirth, email, phonenumber) values('%s', '%s', '%s', '%s', '%s');", table_name, family_name, given_name, date_of_birth, email, phone_number);
            s.executeUpdate(sql_add_patient);
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Unable to add patient to database: %s".format(patients_list_dbUrl));
        }
    }

    private void search_for_drivers() throws FileNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            System.out.println("Failed to find drivers");
            throw new FileNotFoundException("Unable to find drivers for: %s".format(patients_list_dbUrl));
        }
    }

    private Connection get_connection() throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(patients_list_dbUrl, user_name, password);
        } catch (Exception e) {
            System.out.println("failed to connected");
            e.printStackTrace();
            throw new SQLException("Unable to connect to database: %s".format(patients_list_dbUrl));
        }
        return conn;
    }
}
