package Data;

import java.io.Serializable;

public class User implements Serializable {

    String username, password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void print_username(){
        System.out.println(String.format("%s", username));
    }

    public String get_username(){
        return username;
    }

    public String get_password(){
        return password;
    }

}
