package Database;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;

public class Manage_Patient_Db {

    public Manage_Patient_Db() throws FileNotFoundException {
        String patients_list_dbUrl = "jdbc:patients_postgres://localhost:5432/postgres";
        System.out.println(patients_list_dbUrl);

        try {
            // Registers the driver
            Class.forName("org.postgresql.Driver");
            Connection conn= DriverManager.getConnection(patients_list_dbUrl, "postgres", "");
        } catch (Exception e) {
            System.out.println("Failed");
            throw new FileNotFoundException("Unable to connect to: %s".format(patients_list_dbUrl));
        }


    }

}
