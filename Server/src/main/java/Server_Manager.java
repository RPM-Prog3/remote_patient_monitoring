import Setup.Read_server_properties;

import java.io.IOException;

public class Server_Manager {
    String server_ip;

    public Server_Manager() throws IOException {
        String user_dir = Read_server_properties.get_user_dir();
        String server_config_path = user_dir + "/Server/server_config.properties";
        Read_server_properties server_prop = new Read_server_properties(server_config_path);
        server_ip = server_prop.get_server_ip();
    }

    public void receive_patient_from_client(){

    }

    public void send_patients_to_client(){

    }
}
