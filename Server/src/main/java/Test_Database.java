import Database.Manage_Patient_Db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;

public class Test_Database {
    public static void main(String[] args) throws IOException, SQLException {
        //Manage_Patient_Db pdb = new Manage_Patient_Db();
        //pdb.add_single_patient("Arrowsmith", "Joe", "100398", "ja2116@ic.ac.uk", "0123");

        Client_Manager cm = new Client_Manager();

        cm.request_patients_from_server();
        System.out.println("-----------------");
        cm.send_patient_to_server();
        System.out.println("-----------------");
        System.out.println("Done");

    }
}
