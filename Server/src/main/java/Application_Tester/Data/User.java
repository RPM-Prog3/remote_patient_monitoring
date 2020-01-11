package Application_Tester.Data;

import java.io.Serializable; // Enables the class to be passed through a GSON

public class User implements Serializable {

    private String username, password, email;
    private boolean admin_status;

    public User(String username, String password, String email, boolean admin_status){
        this.username = username;
        this.password = password;
        this.email = email;
        this.admin_status = admin_status;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
        // check valid user
        //retrieve_from_db_email();
        //retrieve_from_db_admin_status();
    }

    public void print_user(){
        System.out.println(String.format("%s - %s - %s", username, email, admin_status));
    }

    private void retrieve_from_db_email(){
        this.email = null;
    }

    private void retrieve_from_db_admin_status() {
        this.admin_status = false; // Need to read from db and also check password and values entered are allowed
    }

    /*
    The functions below need to be made private currently a security issue.
     */

    public String get_username(){
        return username;
    }

    public String get_password(){
        return password;
    }

    public String get_email(){
        return email;
    }

    public boolean get_admin_status(){
        return admin_status;
    }
}
