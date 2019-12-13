import Setup.Read_server_properties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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
        return String.format("http://%s:%s/Server", server_ip, server_port);
    }

    public void request_patients_from_server() throws IOException {
        URL server_url = new URL(get_url());
        HttpURLConnection conn = (HttpURLConnection) server_url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "text/html");
        conn.setRequestProperty("charset", "utf-8");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(server_url.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null){
            System.out.println(inputLine);
        }
        in.close();
    }

    public void send_patient_to_server() throws IOException {
        // Set up the body data
        String message = "Hello servlet";
        byte[] body = message.getBytes(StandardCharsets.UTF_8);

        URL myURL = new URL(get_url());

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


        /////////////////////////////

        System.out.println(conn.getResponseMessage());



    }
}
