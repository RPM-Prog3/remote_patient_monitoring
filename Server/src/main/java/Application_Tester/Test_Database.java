package Application_Tester;

import Application_Tester.Data.Patient_Value;
import Application_Tester.Data.User;
import Application_Tester.Messenger.Server_Messenger;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Test_Database {


    public static void main(String[] args) throws IOException, SQLException {

        Client_Manager cm = new Client_Manager();

        User login_user = new User("Joe", "1234");

        System.out.println("-----------------");
        System.out.println("Test 1");
        Server_Messenger msg_test_1;
        msg_test_1 = cm.get_patients_from_patients_db();
        System.out.println(msg_test_1.get_message());
        System.out.println(msg_test_1.get_success());
        System.out.println("-----------------");
        System.out.println("Test 1");
        msg_test_1 = cm.send_patient_to_add_patients_db("Arrow", "joea", "10", "ja", "1234");
        System.out.println(msg_test_1.get_success());
        System.out.println("-----------------");
        System.out.println("Test 1");
        msg_test_1 = cm.get_patients_from_patients_db();
        System.out.println(msg_test_1.get_message());
        System.out.println(msg_test_1.get_success());
        System.out.println("-----------------");

        System.out.println("Test 2");
        Server_Messenger msg_test_2 = cm.send_user_to_login("Joe", "1234");
        System.out.println(msg_test_2.get_success());
        System.out.println("-----------------");

        System.out.println("Test 3");
        Server_Messenger msg_test_3;
        msg_test_3 = cm.get_users_from_users_db();
        System.out.println(msg_test_3.get_message());
        System.out.println(msg_test_3.get_success());
        System.out.println("-----------------");
        System.out.println("Test 3");
        msg_test_3 = cm.send_user_to_add_users_db("Joe", "1234", "ja", false);
        System.out.println(msg_test_3.get_success());
        System.out.println("-----------------");
        System.out.println("Test 3");
        msg_test_3 = cm.send_user_to_add_users_db("Lapo", "4321", "lp", false);
        System.out.println(msg_test_3.get_success());
        System.out.println("-----------------");
        System.out.println("Test 3");
        msg_test_3 = cm.get_users_from_users_db();
        System.out.println(msg_test_3.get_message());
        System.out.println(msg_test_3.get_success());
        System.out.println("-----------------");

        System.out.println("Test 4");
        Server_Messenger msg_test_4;
        msg_test_4 = cm.send_user_to_login("Joe", "1234");
        System.out.println(msg_test_4.get_success());
        System.out.println("-----------------");

        System.out.println("Test 5");
        Server_Messenger msg_test_5;
        LocalDateTime now = LocalDateTime.now();
        Patient_Value pv = new Patient_Value(1, now,
                60, 20, 37.2,
                180, 90, "");
        msg_test_5 = cm.send_patient_value_to_pv_db(pv);
        System.out.println(msg_test_5.get_success());
        System.out.println("-----------------");

        System.out.println("Done");
    }
}
