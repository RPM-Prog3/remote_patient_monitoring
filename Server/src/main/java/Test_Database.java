import Data.Patient;
import Data.User;

import java.io.IOException;
import java.sql.SQLException;

public class Test_Database {


    public static void main(String[] args) throws IOException, SQLException {

        Client_Manager cm = new Client_Manager();




        System.out.println("-----------------");
        System.out.println("Test 1");
        Messenger msg_test_1 = cm.get_patients_from_patients_db();
        System.out.println(msg_test_1.get_message());
        System.out.println(msg_test_1.get_success());
        System.out.println("-----------------");
        System.out.println("Test 2");
        Messenger msg_test_2 = cm.send_patient_to_add_patients_db("Arrow", "joea", "10", "ja", "1234");
        System.out.println(msg_test_2.get_success());
        System.out.println("-----------------");
        System.out.println("Test 3");
        Messenger msg_test_3 = cm.send_user_to_login("Joe", "1234");
        System.out.println(msg_test_3.get_success());
        System.out.println("-----------------");
        System.out.println("Test 4");
        Messenger msg_test_4 = cm.get_users_from_users_db();
        System.out.println(msg_test_4.get_message());
        System.out.println(msg_test_4.get_success());
        System.out.println("-----------------");
        System.out.println("Test 5");
        Messenger msg_test_5 = cm.send_user_to_add_users_db("Joe", "1234", "ja");
        System.out.println(msg_test_5.get_success());
        System.out.println("-----------------");
        System.out.println("Test 6");
        Messenger msg_test_6 = cm.send_user_to_add_users_db("Lapo", "4321", "lp");
        System.out.println(msg_test_6.get_success());
        System.out.println("-----------------");
        System.out.println("Test 7");
        Messenger msg_test_7 = cm.get_users_from_users_db();
        System.out.println(msg_test_7.get_message());
        System.out.println(msg_test_7.get_success());
        System.out.println("-----------------");
        System.out.println("Test 8");
        Messenger msg_test_8 = cm.send_user_to_login("Joe", "1234");
        System.out.println(msg_test_8.get_success());
        System.out.println("-----------------");
        System.out.println("Done");
    }
}
