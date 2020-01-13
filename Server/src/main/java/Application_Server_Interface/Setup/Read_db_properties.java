package Application_Server_Interface.Setup;

import java.io.IOException;

public class Read_db_properties extends Read_Property_File {

    public Read_db_properties(String db_config_path) throws IOException {
        super(db_config_path);
    }

    public String get_db_user_name() {
        return prop.getProperty("db_user_name");
    }

    public String get_db_password() {
        return prop.getProperty("db_password");
    }

    public String get_db_name() {
        return prop.getProperty("db_name");
    }
}