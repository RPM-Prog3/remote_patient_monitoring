package Server_Core.Exception;

public class InvalidUserException extends Exception {
    private boolean user_exists, user_is_admin;

    public InvalidUserException(boolean user_exists, boolean user_is_admin){
        this.user_exists = user_exists;
        if (!user_exists) {
            assert user_is_admin = false : "Invalid logic, user does not exist but tried to be set as admin";
        } else {
            this.user_is_admin = user_is_admin;
        }
    }

    public boolean get_user_exists(){
        return user_exists;
    }

    public boolean get_user_is_admin(){
        return user_is_admin;
    }
}