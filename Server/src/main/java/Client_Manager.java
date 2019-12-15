import Data.Patient;
import Data.User;
import Setup.Read_server_properties;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.rmi.server.ExportException;

public class Client_Manager {
    String server_ip;
    String server_port;

    public Client_Manager() throws IOException {
        String user_dir = Read_server_properties.get_user_dir();
        String server_config_path = user_dir + "/Server/server_config.properties";
        Read_server_properties server_prop = new Read_server_properties(server_config_path);
        server_ip = server_prop.get_server_ip();
        server_port = server_prop.get_server_port();
    }

    private String get_url(){
        return String.format("http://%s:%s/Server/rpm", server_ip, server_port);
    }

    public void request_patients_from_server() throws IOException {
        make_get_request(get_url());
    }

    public void send_patient_to_server(String familyname, String givenname,
                                       String dofbirth, String email,
                                       String phonenumber) throws IOException {
        Patient p = new Patient(familyname, givenname, dofbirth, email, phonenumber);
        send_patient_to_server(p);
    }

    public void send_patient_to_server(Patient p) throws IOException {
        Gson p_gson = new Gson();
        String p_json_string = p_gson.toJson(p);
        String url = String.format("%s/add_patient", get_url());
        make_post_request(url, p_json_string);
    }

    public void send_user_to_server(String username, String password) throws IOException {
        User u = new User(username, password);
        send_user_to_server(u);
    }

    public void send_user_to_server(User u) throws IOException {
        Gson u_json = new Gson();
        String u_json_string = u_json.toJson(u);
        String url = String.format("%s/login", get_url());
        make_post_request(url, u_json_string);
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

    private void make_post_request(String url, String message) throws IOException {
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
        BufferedReader bufferedReader = new BufferedReader(new
                InputStreamReader(conn.getInputStream(), "utf-8"));
        String inputLine;
        // Read the body of the response
        while ((inputLine = bufferedReader.readLine()) != null) {
            System.out.println(inputLine);
        }
        bufferedReader.close();
    }
}
