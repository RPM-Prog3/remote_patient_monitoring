package Application_Tester.Messenger;

import Application_Tester.Data.User;
import com.google.gson.Gson;

public class Client_Messenger {
    private String request_message;
    private String user_login;

    public Client_Messenger(String request_message, User user){
        this.request_message = request_message;
        Gson user_gson = new Gson();
        this.user_login = user_gson.toJson(user);
    }

    public Client_Messenger(String request_message, String user_login){
        this.request_message = request_message;
        this.user_login = user_login;
    }

    public String get_request_message(){
        return request_message;
    }

    public User get_user_login(){
        Gson gson = new Gson();
        return gson.fromJson(user_login, User.class);
    }

    public String get_user_login_gson(){
        return user_login;
    }
}
