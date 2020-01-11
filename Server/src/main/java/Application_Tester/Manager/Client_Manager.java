package Application_Tester.Manager;

import Application_Tester.Data.Patient;
import Application_Tester.Data.Patient_Value;
import Application_Tester.Data.User;
import Application_Tester.Messenger.Server_Messenger;
import Application_Tester.Setup.Read_server_properties;
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
        System.out.println(user_dir);
        String server_config_path = user_dir + "/server_config.properties";
        System.out.println(server_config_path);
        Read_server_properties server_prop = new Read_server_properties(server_config_path);
        server_ip = server_prop.get_server_ip();
        server_port = server_prop.get_server_port();
    }

    private String get_url(){
        return String.format("http://%s:%s/Server/rpm", server_ip, server_port);
    }

    public Server_Messenger send_patient_value_to_pv_db(Patient_Value pv) throws IOException {
        Gson pv_gson = new Gson();
        String pv_json_string = pv_gson.toJson(pv);
        String url = String.format("%s/add_patient_value", get_url());
        return get_post_messenger(url, pv_json_string);
    }

    //public Application_Tester.Messenger.Server_Messenger get_patients_from_patients_db(User login_user) throws IOException {
    public Server_Messenger get_patients_from_patients_db() throws IOException {
        String need_to_login = "";
        String url = String.format("%s/request_patients", get_url());
        return get_post_messenger(url, need_to_login);
    }

    //public Application_Tester.Messenger.Server_Messenger send_patient_to_add_patients_db(User login_user, String familyname, String givenname,
    public Server_Messenger send_patient_to_add_patients_db(String familyname, String givenname,
                                                            String dofbirth, String email,
                                                            String phonenumber) throws IOException {
        Patient p = new Patient(familyname, givenname, dofbirth, email, phonenumber);
        return send_patient_to_add_patients_db(p);
    }

    //public Application_Tester.Messenger.Server_Messenger send_patient_to_add_patients_db(User login_user, Patient p) throws IOException {
    public Server_Messenger send_patient_to_add_patients_db(Patient p) throws IOException {
        Gson p_gson = new Gson();
        String p_json_string = p_gson.toJson(p);
        String url = String.format("%s/add_patient", get_url());
        return get_post_messenger(url, p_json_string);
    }

    public Server_Messenger send_user_to_add_users_db(String username, String password, String email, boolean admin_status) throws IOException {
        User u = new User(username, password, email, admin_status);
        return send_user_to_add_users_db(u);
    }

    public Server_Messenger send_user_to_add_users_db(User u) throws IOException {
        Gson u_gson = new Gson();
        String u_json_string = u_gson.toJson(u);
        String url = String.format("%s/add_user", get_url());
        return get_post_messenger(url, u_json_string);
    }

    public Server_Messenger get_users_from_users_db() throws IOException {
        String need_to_login = "";
        String url = String.format("%s/request_users", get_url());
        return get_post_messenger(url, need_to_login);
    }

    public Server_Messenger send_user_to_login(String username, String password) throws IOException {
        User u = new User(username, password);
        return send_user_to_login(u);
    }

    public Server_Messenger send_user_to_login(User u) throws IOException {
        Gson u_json = new Gson();
        String u_json_string = u_json.toJson(u);
        String url = String.format("%s/login", get_url());
        return get_post_messenger(url, u_json_string);
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
