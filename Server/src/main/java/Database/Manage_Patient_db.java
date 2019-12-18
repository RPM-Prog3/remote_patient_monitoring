package Database;

import Data.Patient;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.sql.*;

public class Manage_Patient_db extends Manage_db {
    private String table_name = "patients";

    public Manage_Patient_db() throws IOException, SQLException {
        super();
        String sql_create_table = String.format("create table %s (\n" +
                "   id SERIAL PRIMARY KEY,\n" +
                "   familyname varchar(128) NOT NULL,\n" +
                "   givenname varchar(128) NOT NULL,\n" +
                "   dofbirth varchar(128) NOT NULL,\n" +
                "   email varchar(128) NOT NULL,\n" +
                "   phonenumber varchar(32)\n" +
                ");", table_name);
        init_table(table_name, sql_create_table);
    }

    public void add_patient(Patient p) throws SQLException {
        String familyname = p.get_familyname();
        String givenname = p.get_givenname();
        String dateofbirth = p.get_dofbirth();
        String email = p.get_email();
        String phonenumber = p.get_phonenumber();
        add_patient(familyname, givenname, dateofbirth, email, phonenumber);
    }

    private void add_patient(String family_name, String given_name,
                             String date_of_birth, String email,
                             String phone_number) throws SQLException {
        String sql_add_patient = String.format("insert into %s (familyname, givenname, dofbirth, email, phonenumber) values('%s', '%s', '%s', '%s', '%s');",
                table_name, family_name, given_name, date_of_birth, email, phone_number);
        String exception_msg = String.format("Unable to add patient to database - table: %s - %s",
                db_url, table_name);
        execute_query(sql_add_patient, exception_msg);
    }

    private void remove_patient(Patient p){
        throw new NotImplementedException();
    }

    public ResultSet get_patients_resultSet() throws SQLException {
        String sql_get_patients = String.format("SELECT * FROM %s WHERE id >= 1", table_name);
        String exception_msg = String.format("Unable to get patients from %s", table_name);
        return execute_query(sql_get_patients, exception_msg);
    }

    public void print_rs(ResultSet rs) throws SQLException {
        while (rs.next()){
            System.out.println(String.format("%s,%s", rs.getString("familyname"), rs.getString("givenname")));
        }
    }
}
