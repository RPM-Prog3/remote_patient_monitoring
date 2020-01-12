package Application_Tester;

import Application_Tester.Data.Patient_Value;
import Application_Tester.Data.User;
import Application_Tester.Manager.Client_Manager;
import Application_Tester.Messenger.Server_Messenger;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Test_Database {

    public static void main(String[] args) throws IOException, SQLException {

        Client_Manager cm = new Client_Manager();

        User login_user = new User("admin", "admin");

        // Test 1 - a
        Server_Messenger msg_test_1a = cm.get_patients_from_patients_db(login_user);
        System.out.println("test1a: " + msg_test_1a.get_valid_user() + " - " + msg_test_1a.get_message());
        assert msg_test_1a.get_success() : "Failed to get patients from db test 1a";

        // Test 1 - b
        Server_Messenger msg_test_1b = cm.send_patient_to_add_patients_db("Arrow", "joea", "10", "ja", "1234", login_user);
        System.out.println("test1b: " + msg_test_1b.get_valid_user() + " - " + msg_test_1b.get_message());
        assert msg_test_1b.get_success() : "Failed to add patient to db test 1b";

        // Test 1 - c
        Server_Messenger msg_test_1c = cm.get_patients_from_patients_db(login_user);
        System.out.println("test1c: " + msg_test_1c.get_valid_user() + " - " + msg_test_1c.get_message());
        assert msg_test_1b.get_success() : "Failed to get patients from db test 1c";

        // Test 2
        Server_Messenger msg_test_2 = cm.send_user_to_login("Joe", "1234");
        System.out.println("test2: " + msg_test_2.get_valid_user() + " - " + msg_test_2.get_message());
        assert msg_test_2.get_success() : "Failed to send user to login to users db test 2";

        // Test 3 - a
        Server_Messenger msg_test_3a = cm.get_users_from_users_db(login_user);
        System.out.println("test3a: " + msg_test_3a.get_valid_user() + " - " + msg_test_3a.get_message());
        assert msg_test_3a.get_success() : "Failed to get users from users_db";

        // Test 3 - b
        Server_Messenger msg_test_3b = cm.send_user_to_add_users_db("Joe", "1234", "ja", false, login_user);
        System.out.println("test3b: " + msg_test_3b.get_valid_user() + " - " + msg_test_3b.get_message());
        assert msg_test_3b.get_success()  : "Failed to add user to users db";

        // Test 3 - c
        Server_Messenger msg_test_3c = cm.send_user_to_add_users_db("Lapo", "4321", "lp", false, login_user);
        System.out.println("test3c: " + msg_test_3c.get_valid_user() + " - " + msg_test_3c.get_message());
        assert msg_test_3c.get_success() : "Failed to add user to users db";

        // Test 3 - d
        Server_Messenger msg_test_3d = cm.get_users_from_users_db(login_user);
        System.out.println("test3d: " + msg_test_3d.get_valid_user() + " - " + msg_test_3d.get_message());
        assert msg_test_3d.get_success() : "Failed to get users from users_db";

        // Test 4
        Server_Messenger msg_test_4 = cm.send_user_to_login("admin", "admin");
        System.out.println("test4: " + msg_test_4.get_valid_user() + " - " + msg_test_4.get_message());
        assert msg_test_4.get_success() : "Failed to send user to login";

        // Test 5
        LocalDateTime now = LocalDateTime.now();
        Patient_Value pv = new Patient_Value(1, now,
                60, 20, 37.2,
                180, 90, "");
        Server_Messenger msg_test_5 = cm.send_patient_value_to_pv_db(pv, login_user);
        System.out.println("test5: " + msg_test_5.get_valid_user() + " - " + msg_test_5.get_message());
        assert msg_test_5.get_success() : "Failed to add patient value to patient values database";

        // need to add some failure cases such as adding patient value for a non-existent user.

        System.out.println("Done");
    }
}
