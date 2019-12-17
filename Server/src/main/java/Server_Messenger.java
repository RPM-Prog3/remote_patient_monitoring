import com.google.gson.Gson;

public class Server_Messenger {
    private boolean success;
    private String message;

    public Server_Messenger(){}

    public Server_Messenger(boolean success, String message){
        this.success = success;
        this.message = message;
    }

    public String get_message(){
        return message;
    }

    public boolean get_success(){
        return success;
    }

    public void set_message(String message){
        this.message = message;
    }

    public void set_success(Boolean success){
        this.success = success;
    }


}