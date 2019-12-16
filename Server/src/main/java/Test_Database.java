import Data.User;

import java.io.IOException;
import java.sql.SQLException;

public class Test_Database {


    public static void main(String[] args) throws IOException, SQLException {

        Client_Manager cm = new Client_Manager();

        User current_user = new User("Joe", "1234");

//        cm.request_patients_from_server();
        System.out.println("-----------------");
        cm.send_patient_to_add_patient_db("Arrow", "joea", "10", "ja", "1234");
        System.out.println("-----------------");
        cm.send_user_to_login(current_user);
        System.out.println("-----------------");
        System.out.println("Done");
    }
}
