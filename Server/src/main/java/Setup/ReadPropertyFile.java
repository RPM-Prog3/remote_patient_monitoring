package Setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;



public class ReadPropertyFile {
    Properties prop;

    public ReadPropertyFile(String config_path) throws IOException {
        // Resource to setup properties: https://medium.com/@sonaldwivedi/how-to-read-config-properties-file-in-java-6a501dc96b25
        prop = new Properties();
        String config_path = System.getProperty("user.dir").toString().replace("\\", "/").replace(" ", "%20") + "/Server/config.properties";
        check_file_exists(config_path);
        FileInputStream input_stream = new FileInputStream(config_path);
        prop.load(input_stream);
        check_config_not_unedited();
    }

    private void check_file_exists(String path) throws FileNotFoundException {
        File tmpDir = new File(path);
        boolean exists = tmpDir.exists();
        if (!exists){
            throw new FileNotFoundException("config.properties has now been found did you run setup.sh?");
        }
    }

    private void check_config_not_unedited() throws IllegalArgumentException {
        if (get_db_name().equals("YOUR_DATABASE_NAME_HERE")){
            System.out.println("error");
            throw new IllegalArgumentException("db name has not be changed in Server/config.properties. Please change this.");
        }
        if (get_db_password().equals("YOUR_PASSWORD_HERE")){
            System.out.println("error");
            throw new IllegalArgumentException("db password has not be changed in Server/config.properties. Please change this.");
        }
        if (get_db_user_name().equals("YOUR_USER_NAME_HERE")){
            System.out.println("error");
            throw new IllegalArgumentException("db user name has not be changed in Server/config.properties. Please change this.");
        }
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
