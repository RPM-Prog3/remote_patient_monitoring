import Database.Manage_Patient_Db;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Test_Database {
    public static void main(String[] args) throws FileNotFoundException, SQLException {
        System.out.println(("Testing db"));
        Manage_Patient_Db pdb = new Manage_Patient_Db();
    }
}
