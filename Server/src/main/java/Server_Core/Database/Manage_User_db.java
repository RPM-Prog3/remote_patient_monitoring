package Server_Core.Database;

import Application_Tester.Data.User;

import Server_Core.Exception.InvalidUserException;
import com.google.gson.Gson;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.sql.*;

public class Manage_User_db extends Manage_db {
    private String table_name = "users";

    public Manage_User_db() throws IOException, SQLException {
        super();
        String sql_create_table = String.format("create table %s (\n" +
                "   id SERIAL PRIMARY KEY,\n" +
                "   username varchar(128) NOT NULL,\n" +
                "   password varchar(128) NOT NULL,\n" +
                "   email varchar(128) NOT NULL,\n" +
                "   admin_status BOOLEAN NOT NULL\n" +
                ");", table_name);
        boolean table_exists_before = init_table(table_name, sql_create_table);
        if (!table_exists_before){
            // add admin user so that admin can setup the database. This user should be deleted as soon as another admin is made.
            add_user("admin", "admin", "N.A.", true);
        }
    }

    public void add_user(User u) throws SQLException {
        String username = u.get_username();
        String password = u.get_password();
        String email = u.get_email();
        boolean admin_status = u.get_admin_status();
        add_user(username, password, email, admin_status);
    }

    public void add_user(String username, String password, String email, boolean admin_status) throws SQLException {
        String sql_add_user = String.format("insert into %s (username, password, email, admin_status) values('%s', '%s', '%s', '%s');",
                table_name, username, password, email, admin_status);
        String exception_msg = String.format("Unable to add user to database - table: %s - %s",
                db_url, table_name);
        execute_update(sql_add_user, exception_msg);
    }

    private void remove_user(String username, String password){
        throw new NotImplementedException();
    }

    public String get_users() throws SQLException {
        String sql_get_users = String.format("SELECT * FROM %s WHERE id >= 1", table_name);
        String exception_msg = String.format("Unable to get users from %s", table_name);
        String[] rs_strings = {"username", "password"};
        return execute_query_with_gson(sql_get_users, exception_msg, rs_strings);
    }

    public boolean find_user(User check_user) throws SQLException {
        System.out.println("finding user");
        String users = get_users();
        System.out.println(users);
        Gson gson = new Gson();
        String[][] users_arr = gson.fromJson(users, String[][].class);

        for (int i = 0; i < users_arr.length; i++){
            if (users_arr[i][0].equals(check_user.get_username()) & users_arr[i][1].equals(check_user.get_password())){
                return true;
            }
        }
        return false;
    }

    public boolean is_user_admin(User check_user) throws InvalidUserException, SQLException {
        System.out.println("Searching for admin status of: " + check_user.get_username());
        if (find_user(check_user)) {
            String users = get_users();
            System.out.println(users);
            Gson gson = new Gson();
            String[][] users_arr = gson.fromJson(users, String[][].class);
            for (int i = 0; i < users_arr.length; i++){
                System.out.println(users_arr[i][3]);
                if (users_arr[i][3].equals("true")){
                    return true;
                }
            }
        } else {
            throw new InvalidUserException(false, false);
        }
        return false;
    }
}
