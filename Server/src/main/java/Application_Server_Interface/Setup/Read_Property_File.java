package Application_Server_Interface.Setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class Read_Property_File {
    Properties prop;
    private String config_path;

    public Read_Property_File(String config_path) throws IOException {
        // Resource accessed to setup properties: https://medium.com/@sonaldwivedi/how-to-read-config-properties-file-in-java-6a501dc96b25
        prop = new Properties();
        this.config_path = config_path;

        // System.out.println("Reading property file: " + config_path);

        // need to check the file path parent directory is remote_patient_monitoring

        check_file_exists();
        load_prop();
        check_config_not_unedited();
    }

    protected void check_file_exists() throws FileNotFoundException {
        File tmpDir = new File(config_path);
        boolean exists = tmpDir.exists();
        if (!exists){
            throw new FileNotFoundException(String.format("%s has now been found did you run setup.sh?", config_path));
        }
    }

    protected void load_prop() throws IOException {
        FileInputStream input_stream = new FileInputStream(config_path);
        prop.load(input_stream);
    }

    protected void check_config_not_unedited() throws IllegalArgumentException {
        Set<String> keys = prop.stringPropertyNames();
        for (String key : keys) {
            if (prop.getProperty(key).equals("YOUR_VARIABLE_HERE")){
                throw new IllegalArgumentException(String.format("%s has not be changed in %s. Please change this.", key, config_path));
            }
        }
    }

    public static String get_user_dir(boolean remove_server_or_application){
        String path = System.getProperty("user.dir").toString().replace("\\", "/").replace(" ", "%20");
        if( remove_server_or_application){
            File f = new File(path);
            String dir_name = f.getName();
            if (dir_name.equals("Server") || dir_name.equals("Application")) {
                path = f.getParent();
            }
        }
        return path;
    }
}