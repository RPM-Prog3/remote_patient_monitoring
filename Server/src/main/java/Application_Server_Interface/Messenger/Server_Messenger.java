package Application_Server_Interface.Messenger;

public class Server_Messenger {
    /* The message the servers send to the client saying if it
    has been successfully sent and executed and the message recieved. */

    private boolean success, valid_user;
    private String message;

    public Server_Messenger(){
        success = false;
        valid_user = false;
        message = "";
    }

    public Server_Messenger(boolean success, boolean valid_user, String message){
        this.success = success;
        this.valid_user = valid_user;
        this.message = message;
    }

    public String get_message(){
        return message;
    }

    public boolean get_valid_user(){
        return valid_user;
    }

    public boolean get_success(){
        return success;
    }

    public void set_message(String message){
        this.message = message;
    }

    public void set_valid_user(Boolean valid_user){
        this.valid_user = valid_user;
    }

    public void set_success(Boolean success){
        this.success = success;
    }
}