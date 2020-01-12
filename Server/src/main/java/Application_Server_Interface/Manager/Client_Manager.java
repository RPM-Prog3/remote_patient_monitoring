package Application_Server_Interface.Manager;

import Application_Server_Interface.Data.Patient;
import Application_Server_Interface.Data.Patient_Value;
import Application_Server_Interface.Data.User;
import Application_Server_Interface.Messenger.Client_Messenger;
import Application_Server_Interface.Messenger.Server_Messenger;
import Application_Server_Interface.Setup.Read_server_properties;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Client_Manager {
    String server_ip;
    String server_port;

    public Client_Manager() throws IOException {
        String user_dir = Read_server_properties.get_user_dir(false);
        String server_config_path = user_dir + "/server_config.properties";
        Read_server_properties server_prop = new Read_server_properties(server_config_path);
        server_ip = server_prop.get_server_ip();
        server_port = server_prop.get_server_port();
    }

    private String get_url(){
        return String.format("http://%s:%s/Server/rpm", server_ip, server_port);
    }

    public Server_Messenger send_patient_value_to_pv_db(Patient_Value pv, User login_user) throws IOException {
        String url = String.format("%s/add_patient_value", get_url());
        Gson gson = new Gson();
        String pv_json_string = gson.toJson(pv);
        String json_msg = get_json_msg(pv_json_string, login_user);
        return get_post_messenger(url, json_msg);
    }

    public Server_Messenger get_patients_from_patients_db(User login_user) throws IOException {
        String url = String.format("%s/request_patients", get_url());
        String json_msg = get_json_msg("", login_user);
        return get_post_messenger(url, json_msg);
    }

    public Server_Messenger send_patient_to_add_patients_db(String familyname, String givenname,
                                                            String dofbirth, String email,
                                                            String phonenumber, User login_user) throws IOException {
        Patient p = new Patient(familyname, givenname, dofbirth, email, phonenumber);
        return send_patient_to_add_patients_db(p, login_user);
    }

    public Server_Messenger send_patient_to_add_patients_db(Patient p, User login_user) throws IOException {
        String url = String.format("%s/add_patient", get_url());
        Gson gson = new Gson();
        String p_json_string = gson.toJson(p);
        String json_msg = get_json_msg(p_json_string, login_user);
        return get_post_messenger(url, json_msg);
    }

    public Server_Messenger send_user_to_add_users_db(String username, String password, String email, boolean admin_status, User login_user) throws IOException {
        User u = new User(username, password, email, admin_status);
        return send_user_to_add_users_db(u, login_user);
    }

    public Server_Messenger send_user_to_add_users_db(User u, User login_user) throws IOException {
        String url = String.format("%s/add_user", get_url());
        Gson gson = new Gson();
        String u_json_string = gson.toJson(u);
        String json_msg = get_json_msg(u_json_string, login_user);
        return get_post_messenger(url, json_msg);
    }

    public Server_Messenger get_users_from_users_db(User login_user) throws IOException {
        String url = String.format("%s/request_users", get_url());
        String json_msg = get_json_msg("", login_user);
        return get_post_messenger(url, json_msg);
    }

    public Server_Messenger send_user_to_login(String username, String password) throws IOException {
        User u = new User(username, password);
        return send_user_to_login(u);
    }

    public Server_Messenger send_user_to_login(User u) throws IOException {
        String url = String.format("%s/login", get_url());
        String json_msg = get_json_msg("", u);
        return get_post_messenger(url, json_msg);
    }

    private String get_json_msg(String req_msg, User login_user){
        // Combines the request message and login user into a single json to send to the server
        Gson gson = new Gson();
        login_user.print_user();
        Client_Messenger messenger = new Client_Messenger(req_msg, login_user);
        return gson.toJson(messenger);
    }

    private void make_get_request(String url) throws IOException {
        try {
            URL myURL = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/html");
            conn.setRequestProperty("charset", "utf-8");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(myURL.openStream()));
            String inputLine;
            // Read the body of the response
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close();
        } catch (Exception e){
            System.out.println("error in make_get_request");
            e.printStackTrace();
        }
    }

    private Server_Messenger get_post_messenger(String url, String message) throws IOException {
        String response = make_post_request(url, message);
        Gson gson = new Gson();
        return gson.fromJson(response, Server_Messenger.class);
    }

    private String make_post_request(String url, String message) throws IOException {
        // Set up the body data
        byte[] body = message.getBytes(StandardCharsets.UTF_8);
        URL myURL = new URL(url);
        HttpURLConnection conn = null;
        conn = (HttpURLConnection) myURL.openConnection();
        // Set up the header
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "text/html");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(body.length));
        conn.setDoOutput(true);
        // Write the body of the request
        try (OutputStream outputStream = conn.getOutputStream()) {
            outputStream.write(body, 0, body.length);
        }
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream(), "utf-8"));
        } catch (Exception e){
            e.printStackTrace();
            conn.getErrorStream();
        }
        String response = bufferedReader.readLine();
        bufferedReader.close();
        return response;
    }
}
