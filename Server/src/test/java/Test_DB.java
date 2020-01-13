//import Application_Server_Interface.Data.Patient;
//import Application_Server_Interface.Data.User;
//import Application_Server_Interface.Manager.Client_Manager;
//import Application_Server_Interface.Messenger.Server_Messenger;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.io.IOException;

public class Test_DB {

//    @Test
//    public void add_to_users_db() {
//        try {
//            Client_Manager cm = new Client_Manager();
//            User login = new User("name", "password");
//            User admin = new User("admin", "admin");
//            Server_Messenger send_to_db_result = cm.send_user_to_add_users_db(login, admin);
//            Assert.assertTrue(send_to_db_result.get_success());
//            Server_Messenger login_result = cm.send_user_to_login(login);
//            Assert.assertTrue(login_result.get_success());
//        } catch (IOException e) {
//            Assert.fail("Fail due to IO Exception e");
//        }
//    }

//    @Test
//    public void add_to_patients_db(){
//        try {
//            Client_Manager cm = new Client_Manager();
//            User admin = new User("admin", "admin");
//            Patient p = new Patient("familyname", "givenname", "dofb", "email", "phonenumber");
//            Server_Messenger send_to_db_result = cm.send_patient_to_add_patients_db(p, admin);
//            Assert.assertTrue(send_to_db_result.get_success());
//        } catch (IOException e) {
//            Assert.fail("Fail due to IO Exception e");
//        }
//    }

    public void hello_world_db(){
        System.out.println("hello");
    }

}
