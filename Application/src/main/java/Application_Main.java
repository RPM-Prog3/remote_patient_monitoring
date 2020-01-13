import Application_Server_Interface.Data.Patient;
import Application_Server_Interface.Data.User;
import GUI.Main_Frame;
import GUI.UserLogIn;

import java.awt.*;
import java.io.IOException;

public class Application_Main {
    public static void main(String[] args) {
//        UserLogIn welcome = new UserLogIn();
        User login_test = new User("admin", "admin");
        Patient patient = new Patient("010101", "V", "P", "30/07/9898", "vv", "090");
        try {
            Main_Frame summary = new Main_Frame(login_test, patient);
            summary.sendMeanValues();
        } catch (IOException e) {
            System.out.println("oops");
        };
    }
}