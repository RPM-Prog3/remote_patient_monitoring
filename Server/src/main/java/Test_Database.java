import Data.User;

import java.io.IOException;
import java.sql.SQLException;

public class Test_Database {


    public static void main(String[] args) throws IOException, SQLException {

        Client_Manager cm = new Client_Manager();

        User current_user = new User("Joe", "1234");
        User current_user_2 = new User("Joe12", "1234");

//        cm.request_patients_from_server();
        System.out.println("-----------------");
        cm.send_patient_to_add_patient_db("Arrow", "joea", "10", "ja", "1234");
        System.out.println("-----------------");
        cm.send_user_to_login(current_user);
        System.out.println("-----------------");
        cm.get_users_from_user_db();
        System.out.println("-----------------");
        System.out.println("Adding users");
        cm.send_user_to_add_user_db(current_user);
        cm.send_user_to_add_user_db(current_user_2);
        System.out.println("-----------------");
        cm.get_users_from_user_db();
        System.out.println("-----------------");
        System.out.println("Done");
    }
}
