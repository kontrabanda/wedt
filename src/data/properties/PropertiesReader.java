package data.properties;


import java.io.*;
import java.util.Properties;

public class PropertiesReader {
    private final String PATH_TO_DOCUMENTS = "config.properties";
    private Properties prop = new Properties();
    private static PropertiesReader instance = null;

    private PropertiesReader() {
        readPropertiesFile();
    }

    private void readPropertiesFile() {
        try(InputStream input = new FileInputStream(PATH_TO_DOCUMENTS)) {
            prop.load(input);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key) {
        return prop.getProperty(key);
    }

    public static PropertiesReader getInstance() {
        if(instance == null) {
            instance = new PropertiesReader();
        }

        return instance;
    }
}
