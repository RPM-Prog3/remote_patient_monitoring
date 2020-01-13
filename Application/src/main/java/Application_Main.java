import Application_Server_Interface.Data.User;
import GUI.Main_Frame;
import GUI.UserLogIn;

import java.awt.*;

public class Application_Main {
    public static void main(String[] args) {
//        UserLogIn welcome = new UserLogIn();
        User login_test = new User("admin", "admin");
        Main_Frame summary = new Main_Frame("Test", login_test);
    }
}