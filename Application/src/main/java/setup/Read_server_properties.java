package setup;

import java.io.IOException;

public class Read_server_properties extends Read_Property_File {

    public Read_server_properties(String server_config_path) throws IOException {
        super(server_config_path);
    }

    public String get_server_ip() {
        return prop.getProperty("server_ip");
    }

    public String get_server_port() {
        return prop.getProperty("server_port");
    }
}