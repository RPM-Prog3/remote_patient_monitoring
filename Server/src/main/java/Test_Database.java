import Database.Manage_Patient_Db;
import Setup.ReadPropertyFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class Test_Database {
    public static void main(String[] args) throws IOException, SQLException {
        Manage_Patient_Db pdb = new Manage_Patient_Db();
        pdb.add_single_patient("Arrowsmith", "Joe", "100398", "ja2116@ic.ac.uk", "0123");
    }
}
