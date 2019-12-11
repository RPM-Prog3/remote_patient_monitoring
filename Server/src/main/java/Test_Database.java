import Database.Manage_Patient_Db;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Test_Database {
    public static void main(String[] args) throws FileNotFoundException, SQLException {
        Manage_Patient_Db pdb = new Manage_Patient_Db();
        pdb.add_single_patient("Arrowsmith", "Joe", "100398", "ja2116@ic.ac.uk", "0123");
    }
}
