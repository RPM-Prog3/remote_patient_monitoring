package Data;

import java.io.Serializable;

public class User implements Serializable {

    String username, password, email;

    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.email = null;
    }

    public void print_user(){
        System.out.println(String.format("%s - %s", username, email));
    }

    public String get_username(){
        return username;
    }

    public String get_password(){
        return password;
    }

    public String get_email(){
        return email;
    }
}
