package Server_Main.Database;

import Application_Tester.Data.Patient_Value;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.sql.SQLException;

public class Manage_Patient_Values_db extends Manage_db{
    private String table_name = "patient_values";

    public Manage_Patient_Values_db() throws IOException, SQLException {
        super();
        String sql_create_table = String.format("create table %s (\n" +
                "   id SERIAL PRIMARY KEY,\n" +
                "   patient_id int NOT NULL,\n" +
                "   datetime varchar(32) NOT NULL,\n" +
                "   bpm int NOT NULL,\n" +
                "   resp_rate int NOT NULL,\n" +
                "   body_temp real NOT NULL,\n" +
                "   blood_pressure_upper int,\n" +
                "   blood_pressure_lower int,\n" +
                "   abnormality varchar(256) NOT NULL\n" +
                ");", table_name);
        init_table(table_name, sql_create_table);
    }

    public void add_patient_value(Patient_Value pv) throws SQLException {
        int patient_id = pv.get_patient_id();
        String time = pv.get_time();
        int bpm = pv.get_bpm();
        int resp_rate = pv.get_resp_rate();
        double body_temp = pv.get_body_temp();
        int bp_upper = pv.get_blood_pressure_upper();
        int bp_lower = pv.get_blood_pressure_lower();
        String abnormality = pv.get_abnormality();
        add_patient_value(patient_id, time,
                bpm, resp_rate, body_temp,
                bp_upper, bp_lower, abnormality);
    }

    private void add_patient_value(int patient_id, String time,
                                   int bpm, int resp_rate, double body_temp,
                                   int bp_upper, int bp_lower, String abnormality) throws SQLException{
        String sql_add_pv = String.format("insert into %s " +
                "(patient_id, datetime, bpm, resp_rate, body_temp, blood_pressure_upper, blood_pressure_lower, abnormality) " +
                "values('%s','%s','%s','%s','%s','%s','%s','%s');",
                table_name, patient_id, time, bpm, resp_rate, body_temp, bp_upper, bp_lower, abnormality);
        String exception_msg = String.format("Unable to add patient value to database - table: %s - %s",
                db_url, table_name);
        execute_update(sql_add_pv, exception_msg);
    }

    public void remove_patient_value(Patient_Value pv) {
        throw new NotImplementedException();
    }
}