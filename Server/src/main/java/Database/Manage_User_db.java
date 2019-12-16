package Database;

import Data.Patient;
import Data.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
                "   password varchar(128) NOT NULL\n" +
                ");", table_name);
        init_table(table_name, sql_create_table);
    }

    public void add_user(User u) throws SQLException {
        String username = u.get_username();
        String password = u.get_password();
        add_user(username, password);
    }

    public void add_user(String username, String password) throws SQLException {
        String sql_add_user = String.format("insert into %s (username, password) values('%s', '%s');",
                table_name, username, password);
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

//    public boolean find_user(User check_user) throws SQLException {
//        ResultSet rs = get_users_resultSet();
//        while (rs.next()){
//            String rs_un = rs.getString("username");
//            String rs_pw = rs.getString("password");
//            if (check_user.get_username().equals(rs_un) && check_user.get_password().equals(rs_pw)){
//                rs.close();
//                return true;
//            }
//        }
//        rs.close();
//        return false;
//    }
}
