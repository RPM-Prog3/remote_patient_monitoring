import Setup.Read_server_properties;

import java.io.IOException;

public class Client_Manager {
    String server_ip;

    public Client_Manager() throws IOException {
        String user_dir = Read_server_properties.get_user_dir();
        String server_config_path = user_dir + "/Server/server_config.properties";
        Read_server_properties server_prop = new Read_server_properties(server_config_path);
        server_ip = server_prop.get_server_ip();
    }

    private String get_url(){
        String url = "http://" + server_ip + "remote_patient_monitoring";
        return url;
    }

    public void request_patients_from_server(){

    }

    public void send_patient_to_server(){

    }
}
